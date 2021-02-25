package com.dxw.controller.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.service.ImageService;

/**
 *
 * ImageController
 *
 * @description ImageController
 * @author dxw
 * @date 2021年02月25日 10:53
 * @version 1.0.0
 */
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("")
    public String upload(MultipartFile file) {
        return imageService.upload(file);
    }

}
