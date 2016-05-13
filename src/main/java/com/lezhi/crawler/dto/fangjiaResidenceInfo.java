package com.lezhi.crawler.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class FangjiaResidenceInfo {
    private Long residenceId;

    private String residenceName;

    private String address;

    private String district;

    private String block;

    private String propertyArea;

    private String developers;

    private String propertyCompany;

    private Integer buildingNum;

    private Integer totalHouse;

    private Integer residenceAge;

    private BigDecimal volumeRate;

    private BigDecimal greeningRate;

    private String longitude;

    private String latitude;

    private String residenceUrl;

    private String mainHouseType;

    private String priceRange;

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
        this.residenceName = residenceName == null ? null : residenceName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block == null ? null : block.trim();
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers == null ? null : developers.trim();
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany == null ? null : propertyCompany.trim();
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public Integer getTotalHouse() {
        return totalHouse;
    }

    public void setTotalHouse(Integer totalHouse) {
        this.totalHouse = totalHouse;
    }

    public Integer getResidenceAge() {
        return residenceAge;
    }

    public void setResidenceAge(Integer residenceAge) {
        this.residenceAge = residenceAge;
    }

    public BigDecimal getVolumeRate() {
        return volumeRate;
    }

    public void setVolumeRate(BigDecimal volumeRate) {
        this.volumeRate = volumeRate;
    }

    public BigDecimal getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(BigDecimal greeningRate) {
        this.greeningRate = greeningRate;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getResidenceUrl() {
        return residenceUrl;
    }

    public void setResidenceUrl(String residenceUrl) {
        this.residenceUrl = residenceUrl == null ? null : residenceUrl.trim();
    }

    public String getMainHouseType() {
        return mainHouseType;
    }

    public void setMainHouseType(String mainHouseType) {
        this.mainHouseType = mainHouseType == null ? null : mainHouseType.trim();
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange == null ? null : priceRange.trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("residenceId", residenceId)
                .append("residenceName", residenceName).append("address", address)
                .append("district", district).append("block", block).append("propertyArea", propertyArea)
                .append("developers", developers).append("propertyCompany", propertyCompany)
                .append("buildingNum", buildingNum).append("totalHouse", totalHouse)
                .append("residenceAge", residenceAge).append("volumeRate", volumeRate)
                .append("greeningRate", greeningRate).append("longitude", longitude)
                .append("latitude", latitude).append("residenceUrl", residenceUrl)
                .append("mainHouseType", mainHouseType).append("priceRange", priceRange).toString();
    }
}