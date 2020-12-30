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
    /** 要素代码 */
    private Integer adcode;
    /** 父要素 */
    private Integer parent;
    /** 名称 */
    private String name;
    /** 类型 */
    private String type;
    /** 信息 */
    private String properties;
    /** 子要素数量 */
    private Integer childrenNum;
    /** 级别 */
    private String level;
    /** 图形类型 */
    private String geometryType;
    /** 坐标 */
    private String coordinates;
}
