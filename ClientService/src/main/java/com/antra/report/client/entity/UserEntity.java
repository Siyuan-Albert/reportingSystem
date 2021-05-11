package com.antra.report.client.entity;


import javax.persistence.*;

/**
 * @author: Albert
 * @date: 3/10/21 02:15
 * @description:
 */

@Entity(name="user")
public class UserEntity {
    @Id
    String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
}
