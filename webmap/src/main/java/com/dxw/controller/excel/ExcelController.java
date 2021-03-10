package com.dxw.controller.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxw.service.excel.ExcelService;

/**
 *
 * ExcelController
 *
 * @description ExcelController
 * @author dxw
 * @date 2021年03月04日 16:49
 * @version 1.0.0
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("test")
    public String testDealExcel() {
        return excelService.testDealExcel();
    }
}
