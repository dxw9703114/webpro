package com.dxw.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 地图要素
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/29 11:40
 */
@Data
@AllArgsConstructor
public class Feature {
    private Integer adcode;
    private String parent;
    private String name;
    private String type;
    private String properties;
    private Integer childrenNum;
    private String level;
    private String geometryType;
    private String coordinates;
}
