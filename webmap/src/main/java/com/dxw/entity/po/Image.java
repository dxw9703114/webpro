package com.dxw.entity.po;

import lombok.Builder;
import lombok.Data;

/**
 *
 * Image
 *
 * @description Image
 * @author dxw
 * @date 2021年02月26日 14:23
 * @version 1.0.0
 */
@Data
@Builder
public class Image {
    private String id;
    private String imageName;
    private Long size;
    private String url;
}
