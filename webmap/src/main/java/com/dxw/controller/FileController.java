package com.dxw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.service.FileService;

/**
 * 文件Controller
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/25 13:36
 */
@Controller
@RequestMapping("file")
public class FileController {
    @Resource
    private FileService fileService;

    @ResponseBody
    @PostMapping("json/actions/upload")
    public String uploadJSONFile(HttpServletRequest request, MultipartFile file) {
        return fileService.upload(request, file);
    }
}
