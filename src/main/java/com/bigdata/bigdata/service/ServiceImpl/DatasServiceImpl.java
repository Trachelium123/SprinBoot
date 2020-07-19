package com.bigdata.bigdata.service.ServiceImpl;

import com.bigdata.bigdata.dao.DatasDao;
import com.bigdata.bigdata.entity.Datas;
import com.bigdata.bigdata.service.DatasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DatasServiceImpl implements DatasService {
    @Resource
    private DatasDao datasDao;

    @Override
    public List<Datas>findByF1(String date,String name){
//        System.out.println(date+','+name);
        return datasDao.findByF1(date,name);
    }

    @Override
    public List<Datas> findALl() {
        return datasDao.findAll();
    }
}
