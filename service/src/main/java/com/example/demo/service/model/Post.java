/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.model;

/**
 * @author liujh
 * @version V1.0
 * @Title: Result.java
 * @Package com.example.demo.model
 * @Description
 * @date 2020 04-29 23:15.
 */
public class Post {
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private Object author;
    private Long createTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getId() {

        return id;
    }

    public Object getAuthor() {
        return author;
    }

    public Long getCreateTime() {
        return createTime;
    }
}
