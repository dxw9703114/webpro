package com.dxw.service;

/**
 * 打印Service接口
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/31 11:46
 */
public interface PrintService {

    /**
     * 合并路径所在的所有pdf
     * @author dxw
     * @date 2020/12/31 11:47
     * @param paths pdf文件路径
     * @param newPath 合并生成的pdf文件路径
     * @return true or false
     */
    boolean mergePdf(String[] paths, String newPath);

}
