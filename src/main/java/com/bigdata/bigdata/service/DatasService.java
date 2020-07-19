package com.bigdata.bigdata.service;

import com.bigdata.bigdata.entity.Datas;

import java.util.List;

public interface DatasService {
    List<Datas>findByF1(String date,String name);

    List<Datas> findALl();

}
