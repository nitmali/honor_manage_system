package com.ting.honormanage.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 16:12
 */
@Entity
public class ManagerInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column
    @JsonIgnore
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
