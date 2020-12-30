package com.dxw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dxw.entity.po.Feature;

/**
 * 地图要素Mapper接口
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/29 13:54
 */
@Mapper
public interface FeatureMapper {

    int insert(Feature feature);

    int delete(Integer adcode);

    Feature selectByPrimaryKey(Integer adcode);

    List<Feature> selectList(Feature feature);

    List<Feature> selectAll();

}
