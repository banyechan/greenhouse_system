package com.douzi.greenhouse_system.entity;

import java.util.Date;

public class GreenhouseMonitorData {
    private Integer id;

    private Integer greenhouseId;

    private Double airtemp;

    private Double airhumi;

    private Double soiltemp;

    private Double soilhumi1;

    private Double soilhumi2;

    private Double soilhumi3;

    private Double co2;

    private Double sundata;

    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Integer greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public Double getAirtemp() {
        return airtemp;
    }

    public void setAirtemp(Double airtemp) {
        this.airtemp = airtemp;
    }

    public Double getAirhumi() {
        return airhumi;
    }

    public void setAirhumi(Double airhumi) {
        this.airhumi = airhumi;
    }

    public Double getSoiltemp() {
        return soiltemp;
    }

    public void setSoiltemp(Double soiltemp) {
        this.soiltemp = soiltemp;
    }

    public Double getSoilhumi1() {
        return soilhumi1;
    }

    public void setSoilhumi1(Double soilhumi1) {
        this.soilhumi1 = soilhumi1;
    }

    public Double getSoilhumi2() {
        return soilhumi2;
    }

    public void setSoilhumi2(Double soilhumi2) {
        this.soilhumi2 = soilhumi2;
    }

    public Double getSoilhumi3() {
        return soilhumi3;
    }

    public void setSoilhumi3(Double soilhumi3) {
        this.soilhumi3 = soilhumi3;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getSundata() {
        return sundata;
    }

    public void setSundata(Double sundata) {
        this.sundata = sundata;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}