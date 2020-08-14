package com.chm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 */
public class Booking implements Serializable {

    private Integer bid;
    private String school;
    private String applicant;//姓名
    private String campusId;//学号或工号
    private String phone;
    private String email;
    private String useTime;
    private String reason;
    private Date submitTime;
    private String status;

    public Booking(String school, String applicant, String campusId, String phone, String email, String useTime, String reason) {
        this.school = school;
        this.applicant = applicant;
        this.campusId = campusId;
        this.phone = phone;
        this.email = email;
        this.useTime = useTime;
        this.reason = reason;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bid=" + bid +
                ", school='" + school + '\'' +
                ", applicant='" + applicant + '\'' +
                ", campusId='" + campusId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", useTime='" + useTime + '\'' +
                ", reason='" + reason + '\'' +
                ", submitTime=" + submitTime +
                ", status='" + status + '\'' +
                '}';
    }
}
