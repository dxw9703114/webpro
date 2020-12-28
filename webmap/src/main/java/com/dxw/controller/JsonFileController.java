package com.dxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxw.entity.po.JsonFile;
import com.dxw.service.JsonFileService;

/**
 * 地图JSON文件信息Controller
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/28 18:16
 */
@Controller
@RequestMapping("json")
public class JsonFileController {

    @Autowired
    private JsonFileService jsonFileService;

    @PostMapping("")
    @ResponseBody
    public String insert(JsonFile jsonFile) {
        jsonFileService.insert(jsonFile);
        return "success";
    }

}
