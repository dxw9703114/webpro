package com.dxw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dxw.entity.po.JsonFile;

/**
 * 地图JSON文件信息mapper接口
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/28 17:21
 */
@Mapper
public interface JsonFileMapper {

    /**
     * 插入数据
     * @author dxw
     * @date 2020/12/28 18:05
     * @param jsonFile JSON文件信息
     * @return int
     */
    int insert(JsonFile jsonFile);

    /**
     * 删除数据
     * @author dxw
     * @date 2020/12/28 18:06
     * @param id 主键
     * @return int
     */
    int delete(Integer id);

    /**
     * 更新数据
     * @author dxw
     * @date 2020/12/28 18:06
     * @param jsonFile JSON文件信息
     * @return int
     */
    int update(JsonFile jsonFile);

    /**
     * 根据主键查询
     * @author dxw
     * @date 2020/12/28 18:07
     * @param id 主键
     * @return JsonFile
     */
    JsonFile selectById(Integer id);

    /**
     * 根据参数查询
     * @author dxw
     * @date 2020/12/28 18:07
     * @param jsonFile JSON文件信息
     * @return List
     */
    List<JsonFile> selectList(JsonFile jsonFile);

    /**
     * 查询所有
     * @author dxw
     * @date 2020/12/28 18:08
     * @return List
     */
    List<JsonFile> selectAll();

}
