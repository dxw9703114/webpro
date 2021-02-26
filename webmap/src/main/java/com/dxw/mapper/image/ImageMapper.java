package com.dxw.mapper.image;

import org.apache.ibatis.annotations.Mapper;

import com.dxw.entity.po.Image;

/**
 *
 * ImageMapper
 *
 * @description ImageMapper
 * @author dxw
 * @date 2021年02月26日 14:25
 * @version 1.0.0
 */
@Mapper
public interface ImageMapper {

    int insert(Image image);

}
