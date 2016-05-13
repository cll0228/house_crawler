package com.lezhi.crawler.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Table;

@Table(name = "sh_residence_info")
public class VResidenceInfoModel {

    private Integer residenceId;

    private Integer ringId;

    private Integer blockId;

    private Integer districtId;

    private String nResidenceRing;

    private String nResidenceDistrict;

    private String nResidenceBlock;

    private String residenceRing;

    private String residenceDistrict;

    @JsonIgnore
    private String residenceBlock;

    private String residenceName;

    private String residenceAddr;

    @JsonIgnore
    private Double baiduLon;

    @JsonIgnore
    private Double baiduLat;

    private Double residenceLon;

    private Double residenceLat;

    private String residenceType;

    private Integer residenceTypeId;

    private Double residenceBuildingArea;

    public Double getResidenceBuildingArea() {
        return residenceBuildingArea;
    }

    public void setResidenceBuildingArea(Double residenceBuildingArea) {
        this.residenceBuildingArea = residenceBuildingArea;
    }

    private Double aResidenceBuildingArea;

    private Double aResidenceVp;// 小区绿化率

    private Double aResidenceGp;// 小区容积率

    @JsonIgnore
    private Integer aResidenceAmount;

    @JsonIgnore
    private String aResidenceParking;

    @JsonIgnore
    private String residenceDeveloper;

    @JsonIgnore
    private String residenceManager;

    @JsonIgnore
    private String residenceManageFee;

    private String aResidenceAccomplishDatetime;

    @JsonIgnore
    private String residenceDesc;

    @JsonIgnore
    private String residenceFacility;

    @JsonIgnore
    private String residenceURL;

    @JsonIgnore
    private Date latestDealDate;

    @JsonIgnore
    private Integer hasGarage;

    @JsonIgnore
    private Integer hasClub;

    private Integer isavailable;

    @JsonIgnore
    private String residenceIdStr;

    public Integer getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    public Integer getRingId() {
        return ringId;
    }

    public void setRingId(Integer ringId) {
        this.ringId = ringId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getnResidenceRing() {
        return nResidenceRing;
    }

    public void setnResidenceRing(String nResidenceRing) {
        this.nResidenceRing = nResidenceRing;
    }

    public String getnResidenceDistrict() {
        return nResidenceDistrict;
    }

    public void setnResidenceDistrict(String nResidenceDistrict) {
        this.nResidenceDistrict = nResidenceDistrict;
    }

    public String getnResidenceBlock() {
        return nResidenceBlock;
    }

    public void setnResidenceBlock(String nResidenceBlock) {
        this.nResidenceBlock = nResidenceBlock;
    }

    public String getResidenceRing() {
        return residenceRing;
    }

    public void setResidenceRing(String residenceRing) {
        this.residenceRing = residenceRing;
    }

    public String getResidenceDistrict() {
        return residenceDistrict;
    }

    public void setResidenceDistrict(String residenceDistrict) {
        this.residenceDistrict = residenceDistrict;
    }

    public String getResidenceBlock() {
        return residenceBlock;
    }

    public void setResidenceBlock(String residenceBlock) {
        this.residenceBlock = residenceBlock;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    public String getResidenceAddr() {
        return residenceAddr;
    }

    public void setResidenceAddr(String residenceAddr) {
        this.residenceAddr = residenceAddr;
    }

    public Double getBaiduLon() {
        return baiduLon;
    }

    public void setBaiduLon(Double baiduLon) {
        this.baiduLon = baiduLon;
    }

    public Double getBaiduLat() {
        return baiduLat;
    }

    public void setBaiduLat(Double baiduLat) {
        this.baiduLat = baiduLat;
    }

    public Double getResidenceLon() {
        return residenceLon;
    }

    public void setResidenceLon(Double residenceLon) {
        this.residenceLon = residenceLon;
    }

    public Double getResidenceLat() {
        return residenceLat;
    }

    public void setResidenceLat(Double residenceLat) {
        this.residenceLat = residenceLat;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public Integer getResidenceTypeId() {
        return residenceTypeId;
    }

    public void setResidenceTypeId(Integer residenceTypeId) {
        this.residenceTypeId = residenceTypeId;
    }

    public Double getaResidenceBuildingArea() {
        return aResidenceBuildingArea;
    }

    public void setaResidenceBuildingArea(Double aResidenceBuildingArea) {
        this.aResidenceBuildingArea = aResidenceBuildingArea;
    }

    public Double getaResidenceVp() {
        return aResidenceVp;
    }

    public void setaResidenceVp(Double aResidenceVp) {
        this.aResidenceVp = aResidenceVp;
    }

    public Double getaResidenceGp() {
        return aResidenceGp;
    }

    public void setaResidenceGp(Double aResidenceGp) {
        this.aResidenceGp = aResidenceGp;
    }

    public Integer getaResidenceAmount() {
        return aResidenceAmount;
    }

    public void setaResidenceAmount(Integer aResidenceAmount) {
        this.aResidenceAmount = aResidenceAmount;
    }

    public String getaResidenceParking() {
        return aResidenceParking;
    }

    public void setaResidenceParking(String aResidenceParking) {
        this.aResidenceParking = aResidenceParking;
    }

    public String getResidenceDeveloper() {
        return residenceDeveloper;
    }

    public void setResidenceDeveloper(String residenceDeveloper) {
        this.residenceDeveloper = residenceDeveloper;
    }

    public String getResidenceManager() {
        return residenceManager;
    }

    public void setResidenceManager(String residenceManager) {
        this.residenceManager = residenceManager;
    }

    public String getResidenceManageFee() {
        return residenceManageFee;
    }

    public void setResidenceManageFee(String residenceManageFee) {
        this.residenceManageFee = residenceManageFee;
    }

    public String getaResidenceAccomplishDatetime() {
        return aResidenceAccomplishDatetime;
    }

    public void setaResidenceAccomplishDatetime(String aResidenceAccomplishDatetime) {
        this.aResidenceAccomplishDatetime = aResidenceAccomplishDatetime;
    }

    public String getResidenceDesc() {
        return residenceDesc;
    }

    public void setResidenceDesc(String residenceDesc) {
        this.residenceDesc = residenceDesc;
    }

    public String getResidenceFacility() {
        return residenceFacility;
    }

    public void setResidenceFacility(String residenceFacility) {
        this.residenceFacility = residenceFacility;
    }

    public String getResidenceURL() {
        return residenceURL;
    }

    public void setResidenceURL(String residenceURL) {
        this.residenceURL = residenceURL;
    }

    public Date getLatestDealDate() {
        return latestDealDate;
    }

    public void setLatestDealDate(Date latestDealDate) {
        this.latestDealDate = latestDealDate;
    }

    public Integer getHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(Integer hasGarage) {
        this.hasGarage = hasGarage;
    }

    public Integer getHasClub() {
        return hasClub;
    }

    public void setHasClub(Integer hasClub) {
        this.hasClub = hasClub;
    }

    public Integer getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Integer isavailable) {
        this.isavailable = isavailable;
    }

    public String getResidenceIdStr() {
        return residenceIdStr;
    }

    public void setResidenceIdStr(String residenceIdStr) {
        this.residenceIdStr = residenceIdStr;
    }
}
