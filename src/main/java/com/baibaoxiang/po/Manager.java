package com.baibaoxiang.po;
import lombok.Data;

@Data
public class Manager {
    private Integer id;

    private String name;

    private String username;

    private String password;

    private Area area;

    private String path;

    private String title;

    private String salt;



}