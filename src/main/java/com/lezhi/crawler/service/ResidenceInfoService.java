package com.lezhi.crawler.service;

import com.lezhi.crawler.dto.VResidenceInfoModel;
import com.lezhi.crawler.mapper.VResidenceInfoModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cuill on 2016/5/9.
 */
@Service
public class ResidenceInfoService {

    @Autowired(required = true)
    private VResidenceInfoModelMapper vResidenceInfoModelMapper;

    public VResidenceInfoModel getResidenceByResidName(String residenceName) {
        return vResidenceInfoModelMapper.getResidenceByResidName(residenceName);
    }
}
