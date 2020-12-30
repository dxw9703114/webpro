package com.dxw.service;


import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param response HttpServletResponse
     * @param filename 文件名
     * @return String
     */
    String download(HttpServletResponse response, String filename);

    /**
     * 解析地图JOSN文件信息
     * @author dxw
     * @date 2020/12/29 11:17
     * @param
     * @return
     */
    void analyze(File file);
}
