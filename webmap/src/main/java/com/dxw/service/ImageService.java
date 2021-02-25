package com.dxw.service;

import org.springframework.web.multipart.MultipartFile;

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
     * @param
     * @return
     * @author dxw
     * @date 2021/2/25 11:22
     * @version v1.0.0
     */
    String upload(MultipartFile multipartFile);

    /**
     * ImageService
     *
     * @description
     * @param
     * @return
     * @author dxw
     * @date 2021/2/25 11:23
     * @version v1.0.0
     */
    void download();
}
