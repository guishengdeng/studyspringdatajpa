package com.biz.gbck.vo.voucher;

public class VoucherTypeAppVo {
    private Long id;

    private String name;

    private int faceValue;

    private int periodDays;

    private String description;

    private String startTime;

    public VoucherTypeAppVo() {

    }

    public VoucherTypeAppVo(Long id, String name, int faceValue, int periodDays, String description, String startTime) {
        this.id = id;
        this.name = name;
        this.faceValue = faceValue;
        this.periodDays = periodDays;
        this.description = description;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(int periodDays) {
        this.periodDays = periodDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
