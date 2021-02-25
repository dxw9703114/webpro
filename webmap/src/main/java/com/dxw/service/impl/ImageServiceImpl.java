package com.dxw.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.exception.ParameterException;
import com.dxw.service.ImageService;

/**
 *
 * ImageServiceImpl
 *
 * @description ImageServiceImpl
 * @author dxw
 * @date 2021年02月25日 11:21
 * @version 1.0.0
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${file.temp}")
    private String tempFilePath;

    @Override
    public String upload(MultipartFile multipartFile) {
        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) {
            throw new ParameterException("参数错误");
        }
        File dir = new File(tempFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String name = multipartFile.getOriginalFilename();
        String suffix = Optional.ofNullable(name.substring(name.lastIndexOf("."))).orElse(null);
        if (!Objects.equals(suffix, ".jpg") && !Objects.equals(suffix, ".jpeg")
                && !Objects.equals(suffix, ".png") && !Objects.equals(suffix, ".gif")) {
            throw new ParameterException("不支持的文件格式: " + suffix);
        }
        Long size = multipartFile.getSize();
        String filename = UUID.randomUUID() + ".json";
        String path = tempFilePath + File.separator + filename;
        File tempFile = new File(path);
        try {
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public void download() {

    }
}
