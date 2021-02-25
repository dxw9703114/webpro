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


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dxw.entity.po.Feature;
import com.dxw.mapper.FeatureMapper;
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
    @Resource
    private FeatureMapper featureMapper;

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
        analyze(tempFile);
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
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "下载成功";
    }

    @Override
    public void analyze(File file) {
        if (Objects.isNull(file) || !file.exists()) {
            log.error("参数错误: {}", file);
            return;
        }
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(lineIterator)) {
            log.error("获取迭代器失败: {}", lineIterator);
            return;
        }
        StringBuilder content = new StringBuilder();
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            log.info(next);
            content.append(next);
        }
        JSONObject json = new JSONObject(content.toString());
        log.info(json.toString());
        JSONArray features =  json.getJSONArray("features");
        for (int i = 0; i < features.length(); i++) {
            JSONObject fea = features.getJSONObject(i);
            String type = fea.getString("type");
            JSONObject pro = fea.getJSONObject("properties");
            Integer adcode = pro.getInt("adcode");
            String name = pro.getString("name");
            Integer childrenNum = pro.getInt("childrenNum");
            String level = pro.getString("level");
            Integer parent = pro.getJSONObject("parent").getInt("adcode");
            String geometryType = fea.getJSONObject("geometry").getString("type");
            String coordinates = fea.getJSONObject("geometry").getString("coordinates");
            Feature feature = new Feature(adcode, parent, name, type, pro.toString(), childrenNum, level, geometryType, coordinates);
            featureMapper.insert(feature);
        }
    }
}
