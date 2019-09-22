package com.java.redis.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserBean implements Serializable {

    private int id;
    private String name;
    private String address;
    private String alias;
    private int age;
    private int hegiht;
    private BigDecimal money;
    private Long kilometer;
    private Long far;
    private Long way;
    private BigDecimal salary;
    private BigDecimal wifeSalary;
    private BigDecimal totalSalary;
    private String company;
    private String country;
    private String province;
    private String city;
    private String county;
    private String village;
    private String schoolName;
    private int grade;

}
