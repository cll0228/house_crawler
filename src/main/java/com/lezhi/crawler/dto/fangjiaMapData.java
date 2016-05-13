package com.lezhi.crawler.dto;

public class FangjiaMapData {
    private Integer id;

    private Long residenceId;

    private String blockId;

    private String unitId;

    private String blockNumber;

    private String floorTotal;

    private String boomTotal;

    private String blockSort;

    private String blockAddress;

    private Double x;

    private Double y;

    private Double avgX;

    private Double avgY;

    private Long price;

    private Double x8;

    private Double y8;

    private Double priceFormat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId == null ? null : blockId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber == null ? null : blockNumber.trim();
    }

    public String getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(String floorTotal) {
        this.floorTotal = floorTotal == null ? null : floorTotal.trim();
    }

    public String getBoomTotal() {
        return boomTotal;
    }

    public void setBoomTotal(String boomTotal) {
        this.boomTotal = boomTotal == null ? null : boomTotal.trim();
    }

    public String getBlockSort() {
        return blockSort;
    }

    public void setBlockSort(String blockSort) {
        this.blockSort = blockSort == null ? null : blockSort.trim();
    }

    public String getBlockAddress() {
        return blockAddress;
    }

    public void setBlockAddress(String blockAddress) {
        this.blockAddress = blockAddress == null ? null : blockAddress.trim();
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getAvgX() {
        return avgX;
    }

    public void setAvgX(Double avgX) {
        this.avgX = avgX;
    }

    public Double getAvgY() {
        return avgY;
    }

    public void setAvgY(Double avgY) {
        this.avgY = avgY;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Double getX8() {
        return x8;
    }

    public void setX8(Double x8) {
        this.x8 = x8;
    }

    public Double getY8() {
        return y8;
    }

    public void setY8(Double y8) {
        this.y8 = y8;
    }

    public Double getPriceFormat() {
        return priceFormat;
    }

    public void setPriceFormat(Double priceFormat) {
        this.priceFormat = priceFormat;
    }
}