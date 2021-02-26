package com.dxw.controller.image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.entity.po.Image;
import com.dxw.service.image.ImageService;

/**
 *
 * ImageController
 *
 * @description ImageController
 * @author dxw
 * @date 2021年02月25日 10:53
 * @version 1.0.0
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("upload")
    public String upload(MultipartFile file) {
        return imageService.upload(file);
    }

    @GetMapping("list")
    public List<Image> getList() {
        return imageService.queryList();
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable String id) {
        imageService.deleteById(id);
    }
}
