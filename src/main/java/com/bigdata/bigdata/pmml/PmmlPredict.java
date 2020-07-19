package com.bigdata.bigdata.pmml;

import com.alibaba.fastjson.JSONObject;
import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PmmlPredict {
    //将模型定义为全局变量，springboot启动时加载pmml并初始化
    public static Evaluator evaluator;
    //模型初始化方法，springboot启动时执行该方法，然后初始化上面的Evaluator
    public static void initModel(String fileName) throws IOException, SAXException, JAXBException {
        File file = new File(fileName);
        evaluator = new LoadingModelEvaluatorBuilder().load(file).build();
        evaluator.verify();
    }

    //定义一个实用函数，就是python中的print函数，没别的意思
    public static void print(Object... args){
        Arrays.stream(args).forEach(System.out::print);
        System.out.println("");
    }

    // 定义预测函数，htt请求该函数，然后返回预测值
    // 传入的参数是一个json，字段要求和模型的字段保持一致
    public static Integer predict(JSONObject feature){
        // 获取模型定义的特征
        List<? extends InputField> inputFields = evaluator.getInputFields();
        print("模型的特征是：", inputFields);
        // 获取模型定义的目标名称
        List<? extends TargetField> targetFields = evaluator.getTargetFields();
//        print("目标字段是：",targetFields);
        // 示例传进来的json数据
        // String json = {"x1": 1922, "x2": 1704, "x3": 1492, "x4": 1344, "x5": 1777, "x6": 1392, "x7": 1815, "x8": 1694, "x9": 2088,
        //        "x10": 1750, "x11": 1995, "x12": 1442, "x13": 3006, "x14": 1811, "x15": 1842, "x16": 1016, "x17": 1607,
        //        "x18": 1570, "x19": 1562, "x20": 1395, "x21": 1463, "x22": 1794, "x23": 1751, "x24": 1119}
        // 将json转成evaluator要求的map格式，其实就是对key和value再做一层包装而已
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
        for(InputField inputField: inputFields){
            FieldName inputName = inputField.getName();
            String name = inputName.getValue();
            Object rawValue = feature.getDoubleValue(name);
            FieldValue inputValue = inputField.prepare(rawValue);
            arguments.put(inputName, inputValue);
        }

        // 得到特征数据后就是预测了
        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        Map<String, ?> resultRecord = EvaluatorUtil.decode(results);
        Integer y = (Integer) resultRecord.get("y");
        // 打印结果会更加了解其中的封装过程
//        print("预测结果：");
        print(results);
//        print(resultRecord);
//        print(y);
        return y;
    }
}
