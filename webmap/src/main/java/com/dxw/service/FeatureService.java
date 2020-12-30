package com.dxw.service;

import java.util.List;

import com.dxw.entity.po.Feature;

/**
 * 地图要素Service接口
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/30 17:46
 */
public interface FeatureService {

    int insert(Feature feature);

    int delete(Integer adcode);

    Feature selectByPrimaryKey(Integer adcode);

    List<Feature> selectList(Feature feature);

    List<Feature> selectAll();
}
