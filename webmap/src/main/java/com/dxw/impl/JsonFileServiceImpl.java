package com.dxw.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dxw.entity.po.JsonFile;
import com.dxw.mapper.JsonFileMapper;
import com.dxw.service.JsonFileService;

/**
 * 地图JSON文件信息Service接口实现
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/28 18:12
 */
@Service
public class JsonFileServiceImpl implements JsonFileService {

    @Resource
    private JsonFileMapper jsonFileMapper;

    @Override
    public int insert(JsonFile jsonFile) {
        return jsonFileMapper.insert(jsonFile);
    }

    @Override
    public int delete(Integer id) {
        return jsonFileMapper.delete(id);
    }

    @Override
    public int update(JsonFile jsonFile) {
        return jsonFileMapper.update(jsonFile);
    }

    @Override
    public JsonFile selectById(Integer id) {
        return jsonFileMapper.selectById(id);
    }

    @Override
    public List<JsonFile> selectList(JsonFile jsonFile) {
        return jsonFileMapper.selectList(jsonFile);
    }

    @Override
    public List<JsonFile> selectAll() {
        return jsonFileMapper.selectAll();
    }
}
