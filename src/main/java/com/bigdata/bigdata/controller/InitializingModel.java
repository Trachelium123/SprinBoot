package com.bigdata.bigdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.bigdata.bigdata.pmml.PmmlPredict;
import org.springframework.web.bind.annotation.*;

@RestController
public class InitializingModel {
    // 定义index页，也是为了测试网络是否通畅
    @RequestMapping("/")
    public String index() {
        return "hello spring for test";
    }
    // 定义一个接口，从http中接受RequestBody中的字符串，这是一个json的字符串，用fastjson解析成json后直接调用预测函数PmmlPredict.predict进行预测
    @RequestMapping(value="/predict", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public @ResponseBody
    String getModel(@RequestBody JSONObject json,
                    @RequestParam(name = "fileName",required=false)String fileName) {
        // 将字符串解析成json
        System.out.println(json);
        // 调用PmmlPredict.initModel()
        try {
            PmmlPredict.initModel(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 预测
        double y = PmmlPredict.predict(json);
        // 返回
        return String.valueOf(y);
    }
}

