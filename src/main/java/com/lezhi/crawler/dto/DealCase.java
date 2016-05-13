package com.lezhi.crawler.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by Cuill on 2016/5/11.
 */
public class DealCase {
    private Long id;

    private Long residenceId;

    private String houseType;

    private Double houseAera;

    private String floorInfo;

    private String towards;

    private Date signDate;// 签约日期

    private Double signUnitPrice;// 签约单价

    private Double signTotalPrice;// 签约总价

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Double getHouseAera() {
        return houseAera;
    }

    public void setHouseAera(Double houseAera) {
        this.houseAera = houseAera;
    }

    public String getFloorInfo() {
        return floorInfo;
    }

    public void setFloorInfo(String floorInfo) {
        this.floorInfo = floorInfo;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Double getSignUnitPrice() {
        return signUnitPrice;
    }

    public void setSignUnitPrice(Double signUnitPrice) {
        this.signUnitPrice = signUnitPrice;
    }

    public Double getSignTotalPrice() {
        return signTotalPrice;
    }

    public void setSignTotalPrice(Double signTotalPrice) {
        this.signTotalPrice = signTotalPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("residenceId", residenceId)
                .append("houseType", houseType).append("houseAera", houseAera).append("floorInfo", floorInfo)
                .append("towards", towards).append("signDate", signDate)
                .append("signUnitPrice", signUnitPrice).append("signTotalPrice", signTotalPrice).toString();
    }
}