/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.controller;

import com.example.demo.service.PostService;
import com.example.demo.service.model.Post;
import com.example.demo.service.model.Result;
import com.example.demo.service.querier.PostQueryCondition;
import dto.PostDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liujh
 * @version V1.0
 * @Title: HelloController.java
 * @Package com.example.demo.controller
 * @Description
 * @date 2019 04-27 23:48.
 */
@RequestMapping("/post")
@RestController
public class PostController {

    @Resource
    private PostService postService;

    @RequestMapping("/list")
    public Result list(PostDTO dto) {
        List<Post> postList;

        PostQueryCondition condition = new PostQueryCondition();
        condition.setAuthor(dto.getAuthor());
        condition.setTitle(dto.getTitle());
        postList = postService.listPost(condition);

        Result result = new Result();
        result.setData(postList);

        return  result;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, Edward";
    }

}
