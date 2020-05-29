package com.example.demo.service; /******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/


import com.example.demo.dao.PostDao;
import com.example.demo.dao.po.PostPO;
import com.example.demo.service.assembler.PostAssembler;
import com.example.demo.service.model.Post;
import com.example.demo.service.querier.PostQueryCondition;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujh
 * @version V1.0
 * @Title: RedisPool.java
 * @Package com.example.demo.service
 * @Description
 * @date 2019 06-13 22:52.
 */
@Service
public class PostService {

    @Resource
    private PostDao postDao;

    /**
     * list posts
     * @return
     */
   public List<Post> listPost(PostQueryCondition condition){

       PostPO po = new PostPO();
       po.setAuthor(condition.getAuthor());
       po.setTitle(condition.getTitle());
       List<PostPO> poList = postDao.list(po);

       List<Post> postList = Lists.newArrayList(FluentIterable.from(poList)
               .transform(new Function<PostPO,  Post>() {
                   @Override
                   public Post apply(PostPO po) {
                       return PostAssembler.buildPost(po);
                   }
               }).toSet());

       return postList;
   }

}
