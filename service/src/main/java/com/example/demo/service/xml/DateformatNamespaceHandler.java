/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author liujh
 * @version V1.0
 * @Title: DateformatNamespaceHandler.java
 * @Package com.example.demo.service.xml
 * @Description
 * @date 2020 05-29 0:14.
 */
public class DateformatNamespaceHandler extends NamespaceHandlerSupport{
    /**
     *
     */
    public void init() {
        registerBeanDefinitionParser("dateformat", new DateformatDefinitionParser());

    }
}
