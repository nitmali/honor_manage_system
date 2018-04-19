package com.ting.honormanage.entity;

import com.ting.honormanage.model.HonorInfoModel;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 15:58
 */
@Entity
public class HonorInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(nullable = false)
    private Date year;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Level {
        //院级
        COLLEGE,
        //校级
        SCHOOL,
        //市级
        MUNICIPAL,
        //省级
        PROVINCIAL,
        //国家级
        NATIONAL
    }

    public enum Kind {
        //学术研究
        ACADEMIC_RESEARCH,
        //学科竞赛
        DISCIPLINE_COMPETITION,
        //创新创业
        INNOVATION_ENTREPRENEURSHIP,
        //考级考证
        EXAMINATIONS
    }

    public enum Rank {
        //一等奖
        FIRST,
        //二等奖
        SECOND,
        //三等奖
        THIRD,
        //其他
        OTHER
    }

    public enum Status {
        //有效
        EFFECTIVE,
        //失效
        INVALID
    }

    public HonorInfo() {
    }

    public HonorInfo(HonorInfoModel honorInfoModel) {
        this.id = honorInfoModel.idOfHonorInfo();
        this.name = honorInfoModel.nameOfHonorInfo();
        this.level = honorInfoModel.levelOfHonorInfo();
        this.kind = honorInfoModel.kindOfHonorInfo();
        this.rank = honorInfoModel.rankOfHonorInfo();
        this.year = honorInfoModel.yearOfHonorInfo();
        this.status = honorInfoModel.statusOfHonorInfo();
    }

    public void setHonorInfoFromModel(HonorInfoModel honorInfoModel) {
        this.id = honorInfoModel.idOfHonorInfo();
        this.name = honorInfoModel.nameOfHonorInfo();
        this.level = honorInfoModel.levelOfHonorInfo();
        this.kind = honorInfoModel.kindOfHonorInfo();
        this.rank = honorInfoModel.rankOfHonorInfo();
        this.year = honorInfoModel.yearOfHonorInfo();
        this.status = honorInfoModel.statusOfHonorInfo();
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long idOfHonorInfoModel() {
        return id;
    }

    public String nameOfHonorInfoModel() {
        return name;
    }

    public Date yearOfHonorInfoModel() {
        return year;
    }

    public String levelOfHonorInfoModel() {
        String levelCOLLEGE = "院级";
        String levelSCHOOL = "校级";
        String levelMUNICIPAL = "市级";
        String levelPROVINCIAL = "省级";
        String levelNATIONAL = "国家级";

        if (this.level == Level.COLLEGE) {
            return levelCOLLEGE;
        } else if (this.level == Level.SCHOOL) {
            return levelSCHOOL;
        } else if (this.level == Level.MUNICIPAL) {
            return levelMUNICIPAL;
        } else if (this.level == Level.PROVINCIAL) {
            return levelPROVINCIAL;
        } else if (this.level == Level.NATIONAL) {
            return levelNATIONAL;
        } else {
            return null;
        }
    }

    public String kindOfHonorInfoModel() {
        String kindACADEMIC_RESEARCH = "学术研究";
        String kindDISCIPLINE_COMPETITION = "学科竞赛";
        String kindINNOVATION_ENTREPRENEURSHIP = "创新创业";
        String kindEXAMINATIONS = "考级考证";
        if (this.kind == Kind.ACADEMIC_RESEARCH) {
            return kindACADEMIC_RESEARCH;
        } else if (this.kind == Kind.DISCIPLINE_COMPETITION) {
            return kindDISCIPLINE_COMPETITION;
        } else if (this.kind == Kind.INNOVATION_ENTREPRENEURSHIP) {
            return kindINNOVATION_ENTREPRENEURSHIP;
        } else if (this.kind == Kind.EXAMINATIONS) {
            return kindEXAMINATIONS;
        } else {
            return null;
        }
    }

    public String rankOfHonorInfoModel() {
        String rankOTHER = "其他奖项";
        String rankFIRST = "一等奖";
        String rankSECOND = "二等奖";
        String rankTHIRD = "三等奖";

        if (this.rank == Rank.OTHER) {
            return rankOTHER;
        } else if (this.rank == Rank.FIRST) {
            return rankFIRST;
        } else if (this.rank == Rank.SECOND) {
            return rankSECOND;
        } else if (this.rank == Rank.THIRD) {
            return rankTHIRD;
        } else {
            return null;
        }
    }

    public String statusOfHonorInfoModel() {
        String statusEFFECTIVE = "有效";
        String statusINVALID = "失效";
        if (this.status == Status.EFFECTIVE) {
            return statusEFFECTIVE;
        } else if (this.status == Status.INVALID) {
            return statusINVALID;
        } else {
            return null;
        }
    }
}
