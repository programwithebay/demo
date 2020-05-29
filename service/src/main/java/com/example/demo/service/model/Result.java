/******************************************************************************
 * Copyright (C) 2013 - 2020 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.model;

/**
 * @author liujh
 * @version V1.0
 * @Title: Result.java
 * @Package com.example.demo.model
 * @Description
 * @date 2020 04-29 23:15.
 */
public class Result {
    private int ret;
    private String errMsg;
    private Object data;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getRet() {

        return ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Object getData() {
        return data;
    }
}
