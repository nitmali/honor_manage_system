package com.ting.honormanage.model;

import com.ting.honormanage.entity.HonorInfo;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author nitmali@126.com
 * @date 2018/4/9 13:41
 */

@Component
public class HonorInfoModel {
    private Long id;

    public String name;

    private String level;

    private String kind;

    private String rank;

    private Date year;

    private String status;

    public HonorInfoModel() {
    }

    public HonorInfoModel(HonorInfo honorInfo) {
        this.id = honorInfo.idOfHonorInfoModel();
        this.name = honorInfo.nameOfHonorInfoModel();
        this.level = honorInfo.levelOfHonorInfoModel();
        this.kind = honorInfo.kindOfHonorInfoModel();
        this.rank = honorInfo.rankOfHonorInfoModel();
        this.year = honorInfo.yearOfHonorInfoModel();
        this.status = honorInfo.statusOfHonorInfoModel();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long idOfHonorInfo() {
        return id;
    }

    public String nameOfHonorInfo() {
        return name;
    }

    public HonorInfo.Level levelOfHonorInfo() {
        String levelCOLLEGE = "院级";
        String levelSCHOOL = "校级";
        String levelMUNICIPAL = "市级";
        String levelPROVINCIAL = "省级";
        String levelNATIONAL = "国家级";

        if (levelCOLLEGE.equals(this.level)) {
            return HonorInfo.Level.COLLEGE;
        } else if (levelSCHOOL.equals(this.level)) {
            return HonorInfo.Level.SCHOOL;
        } else if (levelMUNICIPAL.equals(this.level)) {
            return HonorInfo.Level.MUNICIPAL;
        } else if (levelPROVINCIAL.equals(this.level)) {
            return HonorInfo.Level.PROVINCIAL;
        } else if (levelNATIONAL.equals(this.level)) {
            return HonorInfo.Level.NATIONAL;
        } else {
            return null;
        }
    }

    public HonorInfo.Rank rankOfHonorInfo() {
        String rankOTHER = "其他奖项";
        String rankFIRST = "一等奖";
        String rankSECOND = "二等奖";
        String rankTHIRD = "三等奖";

        if (rankOTHER.equals(this.rank)) {
            return HonorInfo.Rank.OTHER;
        } else if (rankFIRST.equals(this.rank)) {
            return HonorInfo.Rank.FIRST;
        } else if (rankSECOND.equals(this.rank)) {
            return HonorInfo.Rank.SECOND;
        } else if (rankTHIRD.equals(this.rank)) {
            return HonorInfo.Rank.THIRD;
        } else {
            return null;
        }
    }

    public HonorInfo.Status statusOfHonorInfo() {
        String statusEFFECTIVE = "有效";
        String statusINVALID = "失效";
        if (statusEFFECTIVE.equals(this.status)) {
            return HonorInfo.Status.EFFECTIVE;
        } else if (statusINVALID.equals(this.status)) {
            return HonorInfo.Status.INVALID;
        } else {
            return null;
        }
    }

    public Date yearOfHonorInfo() {
        return year;
    }

    public HonorInfo.Kind kindOfHonorInfo() {
        String kindACADEMIC_RESEARCH = "学术研究";
        String kindDISCIPLINE_COMPETITION = "学科竞赛";
        String kindINNOVATION_ENTREPRENEURSHIP = "创新创业";
        String kindEXAMINATIONS = "考级考证";
        if (kindACADEMIC_RESEARCH.equals(this.kind)) {
            return HonorInfo.Kind.ACADEMIC_RESEARCH;
        } else if (kindDISCIPLINE_COMPETITION.equals(this.kind)) {
            return HonorInfo.Kind.DISCIPLINE_COMPETITION;
        } else if (kindINNOVATION_ENTREPRENEURSHIP.equals(this.kind)) {
            return HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP;
        } else if (kindEXAMINATIONS.equals(this.kind)) {
            return HonorInfo.Kind.EXAMINATIONS;
        } else {
            return null;
        }
    }
}
