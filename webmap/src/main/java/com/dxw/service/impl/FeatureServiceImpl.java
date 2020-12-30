package com.dxw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dxw.entity.po.Feature;
import com.dxw.mapper.FeatureMapper;
import com.dxw.service.FeatureService;

/**
 * 地图要素Service接口实现
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/30 17:48
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    @Resource
    private FeatureMapper featureMapper;

    @Override
    public int insert(Feature feature) {
        return featureMapper.insert(feature);
    }

    @Override
    public int delete(Integer adcode) {
        return featureMapper.delete(adcode);
    }

    @Override
    public Feature selectByPrimaryKey(Integer adcode) {
        return featureMapper.selectByPrimaryKey(adcode);
    }

    @Override
    public List<Feature> selectList(Feature feature) {
        return featureMapper.selectList(feature);
    }

    @Override
    public List<Feature> selectAll() {
        return featureMapper.selectAll();
    }
}
