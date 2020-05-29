/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.assembler;

import com.example.demo.dao.po.PostPO;
import com.example.demo.service.model.Post;

/**
 * @author liujh
 * @version V1.0
 * @Title: PostAssembler.java
 * @Package com.example.demo.service.assembler
 * @Description
 * @date 2020 05-18 23:26.
 */
public class PostAssembler {

    /**
     *
     * @param po
     * @return
     */
    public static Post buildPost(PostPO po){
        Post res = new Post();
        res.setAuthor(po.getAuthor());
        res.setCreateTime(po.getCreateTime());
        res.setId(po.getId());
        res.setTitle(po.getTitle());

        return res;
    }
}
