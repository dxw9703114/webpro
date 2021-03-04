package com.dxw.impl.excel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.dxw.service.excel.ExcelService;

/**
 *
 * ExcelServiceImpl
 *
 * @description ExcelServiceImpl
 * @author dxw
 * @date 2021年03月04日 16:37
 * @version 1.0.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public void testDealExcel() {
        String excelPath = "C:\\Users\\dxw\\Desktop\\开发工作\\刑事协同组件\\产品版数据标准20201030\\产品版数据标准V1.0.1.4\\0211换押-产品版V1.0.1.4-20200821.xlsm";

        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {

                String suffix = excel.getName().substring(excel.getName().lastIndexOf(".") + 1);
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(suffix)){
                    FileInputStream fis = new FileInputStream(excel);
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(suffix)){
                    wb = new XSSFWorkbook(excel);
                }else if ("xlsm".equals(suffix)){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                // 开始解析
                // 读取sheet 1
                Sheet sheet = wb.getSheetAt(1);
                // 第一行是列名，所以不读
                int firstRowIndex = sheet.getFirstRowNum()+1;
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);
                // 遍历行
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        System.out.println(row.getCell(11).toString());
                        //遍历列
//                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
//                            Cell cell = row.getCell(cIndex);
//                            if (cell != null) {
//                                System.out.println(cell.toString());
//                            }
//                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
