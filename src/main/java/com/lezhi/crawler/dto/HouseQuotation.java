package com.lezhi.crawler.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "lj_house_quotation")
public class HouseQuotation {

    @Id
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long residenceid;

    private Long ringid;

    private Date quotationdate;

    public Date getQuotationDate() {
        return quotationdate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationdate = quotationDate;
    }

    private Integer districtid;

    private Long blockid;

    private Long propertytypeid;

    private Long quotationid;

    public Long getQuotationid() {
        return quotationid;
    }

    public void setQuotationid(Long quotationid) {
        this.quotationid = quotationid;
    }

    private Long floorstructureid;

    private Double quotationtotalprice;

    private Double quotationunitprice;

    private Double propertyarea;

    private String floorstructure;

    private String propertytype;

    private String housetype;

    private Long propertyroom;

    private Long propertyting;

    private Long placefloor;

    private Long totalfloor;

    private String towards;

    private Long towardsid;

    private Float basementarea;

    private Integer isavailable;

    private String qresidencename;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getQuotationdate() {

        return quotationdate;
    }

    public void setQuotationdate(Date quotationdate) {
        this.quotationdate = quotationdate;
    }

    public Long getResidenceid() {
        return residenceid;
    }

    public void setResidenceid(Long residenceid) {
        this.residenceid = residenceid;
    }

    public Long getRingid() {
        return ringid;
    }

    public void setRingid(Long ringid) {
        this.ringid = ringid;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Long getBlockid() {
        return blockid;
    }

    public void setBlockid(Long blockid) {
        this.blockid = blockid;
    }

    public Long getPropertytypeid() {
        return propertytypeid;
    }

    public void setPropertytypeid(Long propertytypeid) {
        this.propertytypeid = propertytypeid;
    }

    public Long getFloorstructureid() {
        return floorstructureid;
    }

    public void setFloorstructureid(Long floorstructureid) {
        this.floorstructureid = floorstructureid;
    }

    public Double getQuotationtotalprice() {
        return quotationtotalprice;
    }

    public void setQuotationtotalprice(Double quotationtotalprice) {
        this.quotationtotalprice = quotationtotalprice;
    }

    public Double getQuotationunitprice() {
        return quotationunitprice;
    }

    public void setQuotationunitprice(Double quotationunitprice) {
        this.quotationunitprice = quotationunitprice;
    }

    public Double getPropertyarea() {
        return propertyarea;
    }

    public void setPropertyarea(Double propertyarea) {
        this.propertyarea = propertyarea;
    }

    public String getFloorstructure() {
        return floorstructure;
    }

    public void setFloorstructure(String floorstructure) {
        this.floorstructure = floorstructure == null ? null : floorstructure.trim();
    }

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype == null ? null : propertytype.trim();
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype == null ? null : housetype.trim();
    }

    public Long getPropertyroom() {
        return propertyroom;
    }

    public void setPropertyroom(Long propertyroom) {
        this.propertyroom = propertyroom;
    }

    public Long getPropertyting() {
        return propertyting;
    }

    public void setPropertyting(Long propertyting) {
        this.propertyting = propertyting;
    }

    public Long getPlacefloor() {
        return placefloor;
    }

    public void setPlacefloor(Long placefloor) {
        this.placefloor = placefloor;
    }

    public Long getTotalfloor() {
        return totalfloor;
    }

    public void setTotalfloor(Long totalfloor) {
        this.totalfloor = totalfloor;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards == null ? null : towards.trim();
    }

    public Long getTowardsid() {
        return towardsid;
    }

    public void setTowardsid(Long towardsid) {
        this.towardsid = towardsid;
    }

    public Float getBasementarea() {
        return basementarea;
    }

    public void setBasementarea(Float basementarea) {
        this.basementarea = basementarea;
    }

    public Integer getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Integer isavailable) {
        this.isavailable = isavailable;
    }

    public String getQresidencename() {
        return qresidencename;
    }

    public void setQresidencename(String qresidencename) {
        this.qresidencename = qresidencename == null ? null : qresidencename.trim();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this).append("ljId", id)
                .append("residenceid", residenceid).append("ringid", ringid)
                .append("quotationdate", quotationdate).append("districtid", districtid)
                .append("blockid", blockid).append("propertytypeid", propertytypeid)
                .append("floorstructureid", floorstructureid)
                .append("quotationtotalprice", quotationtotalprice)
                .append("quotationunitprice", quotationunitprice).append("propertyarea", propertyarea)
                .append("floorstructure", floorstructure).append("propertytype", propertytype)
                .append("housetype", housetype).append("propertyroom", propertyroom)
                .append("propertyting", propertyting).append("placefloor", placefloor)
                .append("totalfloor", totalfloor).append("towards", towards).append("towardsid", towardsid)
                .append("basementarea", basementarea).append("isavailable", isavailable)
                .append("qresidencename", qresidencename).toString();
    }
}