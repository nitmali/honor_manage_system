package com.ting.honormanage.entity;

import javax.persistence.*;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 15:36
 */
@Entity
public class GradeInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

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
}
