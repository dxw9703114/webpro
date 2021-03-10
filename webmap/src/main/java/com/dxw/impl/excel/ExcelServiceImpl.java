package com.dxw.impl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.dxw.service.excel.ExcelService;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    public String testDealExcel() {
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
                    return "";
                }

                // 开始解析
                // 读取sheet 1
                Sheet sheet = wb.getSheetAt(2);
                // 第一行是列名，所以不读
                int firstRowIndex = sheet.getFirstRowNum()+1;
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);
                StringBuilder first = new StringBuilder("const obj = {\"ROOT\":{\"XTAJBH\":{\"GA\":{},\"ZFW\":{},\"JCY\":{},\"FY\":{}},\"BGRXX\":{\"BGRJBXX\":{},\"TARXX\":{\"R\":{}}},\"AJ\":{\"AJJBXX\":{}},\"XTXX\":{},\"WSXX\":{\"R\":{}}}};");
                String type1 = "";
                String type2 = "";
                // 遍历行
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex-2; rIndex++) {
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        String cell1 = row.getCell(0).toString();
                        String cell2 = row.getCell(1).toString();
                        String cell3 = row.getCell(2).toString();
                        String bs = row.getCell(4).toString();
                        String rowString = row.getCell(11).toString();
                        System.out.println("1: " + cell1 + ", 2: " + cell2 + ", 3" + cell3);
                        String[] lj = rowString.split("/");
                        StringBuilder line = new StringBuilder("obj");
                        for (int i = 0; i < lj.length; i++) {
                            if (StringUtils.isNotBlank(lj[i])) {
                                line.append(".").append(lj[i]);

                            }
                        }
                        first.append(line).append("='")
                                .append(UUID.randomUUID().toString().replaceAll("-", ""))
                                .append("';");
                        //遍历列
//                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
//                            Cell cell = row.getCell(cIndex);
//                            if (cell != null) {
//                                System.out.println(cell.toString());
//                            }
//                        }
                    }
                }
                return first.toString();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
