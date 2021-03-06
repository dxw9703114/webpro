package com.dxw.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dxw.entity.po.Image;

/**
 *
 * ImageService
 *
 * @description ImageService
 * @author dxw
 * @date 2021年02月25日 11:11
 * @version 1.0.0
 */
public interface ImageService {
    /**
     * ImageService
     *
     * @description
     * @param multipartFile 文件
     * @return String 访问路径
     * @author dxw
     * @date 2021/2/25 11:22
     * @version v1.0.0
     */
    String upload(MultipartFile multipartFile);

    List<Image> queryList();

    void deleteById(String id);
}
