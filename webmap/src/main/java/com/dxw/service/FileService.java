package com.dxw.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件Service
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/25 13:38
 */
public interface FileService {
    /**
     * 文件上传
     * @author dxw
     * @date 2020/12/25 13:39
     * @param file 文件
     * @return  path
     */
    String upload(HttpServletRequest request, MultipartFile file);

    /**
     * 文件下载
     * @author dxw
     * @date 2020/12/25 13:40
     * @param path 路径
     */
    void download(String path);
}
