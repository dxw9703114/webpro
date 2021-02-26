package com.dxw.impl.image;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.entity.po.Image;
import com.dxw.exception.ParameterException;
import com.dxw.mapper.image.ImageMapper;
import com.dxw.service.image.ImageService;

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

    @Value("${file.img}")
    private String imgFilePath;
    @Value("${url.image-url}")
    private String imgUrl;
    @Resource
    private ImageMapper imageMapper;

    @Override
    public String upload(MultipartFile multipartFile) {
        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) {
            throw new ParameterException("参数错误");
        }
        File dir = new File(imgFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            throw new ParameterException("文件名有误");
        }
        String name = multipartFile.getOriginalFilename();
        String suffix = Optional.of(name.substring(name.lastIndexOf("."))).orElse(null);
        if (!Objects.equals(suffix, ".jpg") && !Objects.equals(suffix, ".jpeg")
                && !Objects.equals(suffix, ".png") && !Objects.equals(suffix, ".gif")) {
            throw new ParameterException("不支持的文件格式: " + suffix);
        }
        Long size = multipartFile.getSize();
        String path = imgFilePath + File.separator + name;
        File tempFile = new File(path);
        try {
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = imgUrl + name;
        Image image = Image.builder().id(UUID.randomUUID().toString().replaceAll("-", "")).imageName(multipartFile.getOriginalFilename()).size(size).url(url).build();
        imageMapper.insert(image);
        return url;
    }

    @Override
    public List<Image> queryList() {
        return imageMapper.list();
    }

    @Override
    public void deleteById(String id) {
        imageMapper.deleteById(id);
    }
}
