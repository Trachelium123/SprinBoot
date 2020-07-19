package com.bigdata.bigdata.dao;

import com.bigdata.bigdata.entity.Datas;
import org.python.icu.text.CaseMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DatasDao extends JpaRepository<Datas,Float> {
    String sql = "use bigdata\n" +
            "declare @strsql varchar(50),\n" +
            "@table_name varchar(50),\n" +
            "@table_column varchar(50)\n" +
            "set @table_name=:name\n" +
            "set @table_column=:date\n" +
            "set @strsql='SELECT TOP(100) * from ' + @table_name\n" +
            "exec(@strsql)";
//    String sql = "declare  @strsql varchar(50),\n" +
//            "@table_name varchar(50),\n" +
//            "@date varchar(50)\n" +
//            "set @table_name=:name\n" +
//            "set @date=:date\n" +
//            "set @strsql='select * from '+@table_name +' where column1='+@date \n" +
//            "exec(@strsql)";
    @Query(nativeQuery = true,value = sql)
    List<Datas>findByF1(@Param("date")String date,@Param("name")String name);

    @Query(nativeQuery = true,value = "select * from c2202_20160601")
    public List<Datas>findAll();

}
