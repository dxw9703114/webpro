package com.dxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxw.entity.po.Feature;
import com.dxw.service.FeatureService;

/**
 * 地图要素Controller
 * @author dxw
 * @version 1.1.0
 * @date 2020/12/30 17:44
 */
@Controller
@RequestMapping("feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("")
    @ResponseBody
    public Object insert(Feature feature) {
        featureService.insert(feature);
        return "SUCCESS";
    }

    @DeleteMapping("")
    @ResponseBody
    public Object delete(Integer adcode) {
        featureService.delete(adcode);
        return "SUCCESS";
    }

    @GetMapping("{adcode}")
    @ResponseBody
    public Object select(@PathVariable Integer adcode) {
        return featureService.selectByPrimaryKey(adcode);
    }

    @GetMapping("par")
    @ResponseBody
    public Object selectList(Feature feature) {
        return featureService.selectList(feature);
    }

    @GetMapping("all")
    @ResponseBody
    public Object selectAll() {
        return featureService.selectAll();
    }
}
