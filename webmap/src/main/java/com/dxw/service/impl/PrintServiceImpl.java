package com.dxw.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.dxw.service.PrintService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

/**
 * 打印Service接口实现
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/31 11:49
 */
@Service
public class PrintServiceImpl implements PrintService {

    @Override
    public boolean mergePdf(String[] paths, String newPath) {
        boolean result = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(paths[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newPath));
            document.open();
            for (int i = 0; i < paths.length; i++) {
                PdfReader reader = new PdfReader(paths[i]);
                int pages = reader.getNumberOfPages();
                for (int j = 1; j <= pages; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                reader.close();
            }
            result = true;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(document)) {
                document.close();
            }
        }
        return result;
    }
}
