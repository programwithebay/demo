/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.ontroller;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujh
 * @version V1.0
 * @Title: HelloController.java
 * @Package com.example.demo.controller
 * @Description
 * @date 2019 04-27 23:48.
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/resize")
    public String hello() {

        try{
        Thumbnails.of("e://f27c555b85f1932aa92b930b96667a40.jpg")
                .size(640, 400)
                .toFile("e://f27c555b85f1932aa92b930b96667a40_640_400.jpg");
        }catch (Exception ex){
            return ex.getMessage();
        }
        return "hello,this is a springboot demo";
    }


}
