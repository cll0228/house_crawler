package com.lezhi.crawler.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by Cuill on 2016/5/11.
 */
public class Residence {
    private Long residenceId;

    private String residenceName;

    private String address;

    private String district;

    private String block;

    private String avagePrice;

    private String accomplishDate;

    private String buildingNum;

    private String totalHouse;

    private String volumeRate;// 容积率

    private String greeningRate;// 绿化率

    private String longitude;// 经度

    private String latitude;// 纬度

    private String residenceUrl;

    private String subWayDesc;

    private List<DealCase> dealCaseList;// 交易案例

    public String getSubWayDesc() {
        return subWayDesc;
    }

    public void setSubWayDesc(String subWayDesc) {
        this.subWayDesc = subWayDesc;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getAvagePrice() {
        return avagePrice;
    }

    public void setAvagePrice(String avagePrice) {
        this.avagePrice = avagePrice;
    }

    public String getAccomplishDate() {
        return accomplishDate;
    }

    public void setAccomplishDate(String accomplishDate) {
        this.accomplishDate = accomplishDate;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getTotalHouse() {
        return totalHouse;
    }

    public void setTotalHouse(String totalHouse) {
        this.totalHouse = totalHouse;
    }

    public String getVolumeRate() {
        return volumeRate;
    }

    public void setVolumeRate(String volumeRate) {
        this.volumeRate = volumeRate;
    }

    public String getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(String greeningRate) {
        this.greeningRate = greeningRate;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getResidenceUrl() {
        return residenceUrl;
    }

    public void setResidenceUrl(String residenceUrl) {
        this.residenceUrl = residenceUrl;
    }

    public List<DealCase> getDealCaseList() {
        return dealCaseList;
    }

    public void setDealCaseList(List<DealCase> dealCaseList) {
        this.dealCaseList = dealCaseList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("residenceId", residenceId)
                .append("residenceName", residenceName).append("address", address)
                .append("district", district).append("block", block).append("avagePrice", avagePrice)
                .append("accomplishDate", accomplishDate).append("buildingNum", buildingNum)
                .append("totalHouse", totalHouse).append("volumeRate", volumeRate)
                .append("greeningRate", greeningRate).append("longitude", longitude)
                .append("latitude", latitude).append("residenceUrl", residenceUrl)
                .append("dealCaseList", dealCaseList).toString();
    }
}
