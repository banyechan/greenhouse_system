package com.douzi.greenhouse_system.mapper;

import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GreenhouseMonitorDataMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(GreenhouseMonitorData record);

    int insertSelective(GreenhouseMonitorData record);

    GreenhouseMonitorData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GreenhouseMonitorData record);

    int updateByPrimaryKey(GreenhouseMonitorData record);

    GreenhouseMonitorData selectTheFirst();

    List<GreenhouseMonitorData> listByMap(Map<String,Object> map);

}