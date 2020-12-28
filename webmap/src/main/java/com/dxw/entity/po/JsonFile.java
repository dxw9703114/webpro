package com.dxw.entity.po;

import java.util.Date;

import lombok.Data;

/**
 * 地图JSON文件信息
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/28 17:08
 */
@Data
public class JsonFile {
    /** 主键 */
    private Integer id;
    /** 类型 */
    private String type;
    /** 名称 */
    private String name;
    /** 文件地址 */
    private String url;
    /** 创建时间 */
    private Date createTime;
}
