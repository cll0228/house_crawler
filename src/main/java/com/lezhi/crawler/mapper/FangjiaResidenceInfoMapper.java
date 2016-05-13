package com.lezhi.crawler.mapper;

import com.github.abel533.mapper.Mapper;
import com.lezhi.crawler.dto.FangjiaResidenceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface FangjiaResidenceInfoMapper extends Mapper<FangjiaResidenceInfo> {

    void save(@Param("list") Collection<?> list);
}