package com.lezhi.crawler.mapper;

import com.github.abel533.mapper.Mapper;
import com.lezhi.crawler.dto.VResidenceInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VResidenceInfoModelMapper extends Mapper<VResidenceInfoModel> {

    VResidenceInfoModel getResidenceByResidName(@Param("residenceName") String residenceName);

    List<VResidenceInfoModel> selectResidenceIdByNameLike(@Param("residenceName") String residenceName,
            @Param("residenceAddr") String residenceAddr);
}