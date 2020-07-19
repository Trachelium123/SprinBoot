package com.bigdata.bigdata.controller;

import com.bigdata.bigdata.entity.Datas;
import com.bigdata.bigdata.service.DatasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DatasController {

    @Resource
    private DatasService datasService;

    @RequestMapping("/all")
    public Map<String,Object> findAll(){
        Map<String,Object>jsonMap = new HashMap<>(20);
        List<Datas>infoList = datasService.findALl();
        jsonMap.put("params",infoList);
        return jsonMap;
    }
    @RequestMapping("/date")
    public Map<String,Object> findByF1(@RequestParam(name = "date",required=false)String date,
                                         @RequestParam(name = "name",required=false)String name){
//        date = "'"+date+"'";
        Map<String,Object>jsonMap = new HashMap<>(20);
        List<Datas>infoList = datasService.findByF1(date.toString(),name.toString());
        jsonMap.put("params",infoList);
        return jsonMap;
    }
}
