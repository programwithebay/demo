/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.xml;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;

/**
 * @author liujh
 * @version V1.0
 * @Title: DateformatDefinitionParser.java
 * @Package com.example.demo.service.xml
 * @Description
 * @date 2020 05-29 0:15.
 */
public class DateformatDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return SimpleDateFormat.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String pattern = element.getAttribute("pattern");
        builder.addConstructorArgValue(pattern);
    }
}
