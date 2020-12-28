package com.dxw.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.service.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件Service实现类
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/25 13:41
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.temp}")
    private String tempFilePath;

    @Override
    public String upload(HttpServletRequest request, MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            log.error("参数错误: {}", file);
            return "参数错误";
        }
        File dir = new File(tempFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String name = file.getOriginalFilename();
        assert name != null : "文件名为null";
        String suffix = Optional.ofNullable(name.substring(name.lastIndexOf("."))).orElse(null);
        if (!Objects.equals(suffix, ".js") && !Objects.equals(suffix, ".json")
                && !Objects.equals(suffix, ".geojson") && !Objects.equals(suffix, ".txt")) {
            log.error("不支持的文件格式: {}", suffix);
            return "不支持的文件格式";
        }
        Long size = file.getSize();
        String filename = UUID.randomUUID() + ".json";
        String path = tempFilePath + File.separator + filename;
        File tempFile = new File(path);
        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("上传成功: 文件名({}), 大小({}), 路径({})", name, size, path);
        return filename;
    }

    @Override
    public String download(HttpServletResponse response, String filename) {
        if (Objects.isNull(filename) || Objects.equals(filename, "")){
            log.error("参数异常: {}", filename);
            return "参数异常";
        }
        File file = new File(tempFilePath + File.separator + filename);
        if (!file.exists()) {
            log.error("文件不存在: {}", file);
            return "文件不存在";
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bis)) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(fis)) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载成功";
    }
}
