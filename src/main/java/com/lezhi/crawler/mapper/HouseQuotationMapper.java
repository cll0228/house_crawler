package com.lezhi.crawler.mapper;

import com.github.abel533.mapper.Mapper;
import com.lezhi.crawler.dto.HouseQuotation;

public interface HouseQuotationMapper extends Mapper<HouseQuotation> {

    void saveOrupdate(HouseQuotation houseQuotation);
}