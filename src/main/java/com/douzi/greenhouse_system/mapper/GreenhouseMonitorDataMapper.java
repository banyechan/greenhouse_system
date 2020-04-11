package com.douzi.greenhouse_system.mapper;

import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GreenhouseMonitorDataMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(GreenhouseMonitorData record);

    int insertSelective(GreenhouseMonitorData record);

    GreenhouseMonitorData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GreenhouseMonitorData record);

    int updateByPrimaryKey(GreenhouseMonitorData record);
}