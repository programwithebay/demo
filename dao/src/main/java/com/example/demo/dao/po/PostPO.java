/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.dao.po;

/**
 * @author liujh
 * @version V1.0
 * @Title: PostPO.java
 * @Package com.example.demo.dao.po
 * @Description
 * @date 2020 05-03 2:13.
 */
public class PostPO {
    private int id;
    private String title;
    private Object author;
    private Long createTime;
    private Long offset=0L;
    private Long limit=10L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
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

    public Long getOffset() {
        return offset;
    }

    public Long getLimit() {
        return limit;
    }
}
