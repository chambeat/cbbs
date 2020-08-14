package com.chm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户（批量导入）实体类
 */
@Entity
@Table(name = "user")
public class Excel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String school;
    @Column
    private String name;
    @Column
    private String username;
    @Column
    private String password;
//    private Boolean enabled;

    public Excel() {
    }

    public Excel(Integer id, String school, String name, String username, String password) {
        this.id = id;
        this.school = school;
        this.name = name;
        this.username = username;
        this.password = password;
//        this.enabled = enabled;
    }

    //get 和 set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    public Boolean getEnabled() {
//        return enabled;
//    }

//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
}
