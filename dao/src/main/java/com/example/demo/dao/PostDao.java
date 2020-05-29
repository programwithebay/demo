/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.dao;

import com.example.demo.dao.po.PostPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liujh
 * @version V1.0
 * @Title: Result.java
 * @Package com.example.demo.model
 * @Description
 * @date 2020 04-29 23:15.
 */
@Repository()
public interface PostDao {
    List<PostPO> list(PostPO postPO);
}
