/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.querier;

/**
 * @author liujh
 * @version V1.0
 * @Title: PostQueryCondition.java
 * @Package com.example.demo.service.querier
 * @Description
 * @date 2020 05-18 23:48.
 */
public class PostQueryCondition {
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
