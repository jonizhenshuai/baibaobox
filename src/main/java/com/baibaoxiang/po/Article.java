package com.baibaoxiang.po;

import lombok.Data;
import java.util.Date;
/**
 * @author chenlin
 */
@Data
public class Article {
    private String no;

    private Manager manager;

    private ArticleType articleType;

    private Area area;

    private Integer readNum;

    private Integer likeNum;

    private String title;

    private Date createTime;

    private Integer top;

    private String picturePath;

    private String message;
}