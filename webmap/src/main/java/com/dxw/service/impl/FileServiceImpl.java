package com.dxw.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;

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
        String path = tempFilePath + File.separator + UUID.randomUUID() + ".json";
        File tempFile = new File(path);
        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("上传成功: 文件名({}), 大小({}), 路径({})", name, size, path);
        return path;
    }

    @Override
    public void download(String path) {
        if (Objects.isNull(path) || Objects.equals(path, "")){
            log.error("文件路径异常: {}", path);
        }
    }
}
