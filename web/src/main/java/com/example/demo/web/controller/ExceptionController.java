/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liujh
 * @version V1.0
 * @Title: ExceptionController.java
 * @Package com.example.demo.controller
 * @Description
 * @date 2020 05-02 23:33.
 */
@Controller
//@ControllerAdvice
public class ExceptionController implements  ErrorController{

    @Autowired
    private ErrorAttributes errorAttributes;

    //@ExceptionHandler(Exception.class)
    //@ResponseBody
    //public Hashtable<String,String> dealCommonException(Exception e) {
    //    Hashtable<String,String> error = new Hashtable();
    //    // 此处可以采用 instanceof 判断异常类型
    //    if (e instanceof ArithmeticException) {
    //        error.put("code", "-1");
    //        error.put("errMsg", "算数异常处理！");
    //        return error;
    //    }
    //    System.err.println("exception");
    //    error.put("code", "-1");
    //    error.put("errMsg", "公共异常处理！");
    //    return error;
    //}


    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest, true);
        final StringBuilder errorDetails = new StringBuilder();
        errorAttributes.forEach((attribute, value) -> {
            errorDetails.append("<tr><td>")
                    .append(attribute)
                    .append("</td><td><pre>")
                    .append(value)
                    .append("</pre></td></tr>");
        });

        return String.format("<html><head><style>td{vertical-align:top;border:solid 1px #666;}</style>"
                + "</head><body><h2>Error Page</h2><table>%s</table></body></html>", errorDetails.toString());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
