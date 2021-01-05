package com.dxw.controller;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片Controller
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/31 18:00
 */
@Controller
@RequestMapping("img")
public class ImageController {

    @Value("${img.testpath}")
    private String path;

    @PostMapping("upload")
    @ResponseBody
    public Object upload(MultipartFile img) {
        if (Objects.isNull(img) || img.isEmpty()) {
            return "参数错误";
        }
        if (StringUtils.isEmpty(path)) {
            return "服务器错误";
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String name = img.getOriginalFilename();
        assert name != null;
        String suffix = name.substring(name.lastIndexOf("."));
        String fileName = path + UUID.randomUUID() + suffix;
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        try {
            img.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @GetMapping("download/{imgname}")
    public void download(@PathVariable("imgname") String imgname) {
        
    }
}
