package com.ting.honormanage.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CheckRecord {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ReportRecord reportRecord;

    @ManyToOne
    private CheckerInfo checkerInfo;

    @Column
    @Lob
    private String opinion;

    @Column(nullable = false)
    private Date checkTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    public void setReportRecord(ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    public CheckerInfo getCheckerInfo() {
        return checkerInfo;
    }

    public void setCheckerInfo(CheckerInfo checkerInfo) {
        this.checkerInfo = checkerInfo;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
