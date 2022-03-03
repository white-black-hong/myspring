
package com.whz.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


import lombok.Data;


/**
 * 外盘现货投资者帐号表 实体对象
 * <p>File：WpSpotAccountInfo.java</p>
 * <p>Title: WpSpotAccountInfo</p>
 * <p>Description:WpSpotAccountInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
public class Account
{
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using= ToStringSerializer.class)
    private java.lang.Long accountId;

    /**经纪公司代码*/
    private java.lang.String brokerID;

    /**投资者帐号*/
    private java.lang.String investorID;

    /**投资者密码*/
    private java.lang.String investorPassword;

    /**认证码*/
    private java.lang.String authCode;

    /**App代码*/
    private java.lang.String appID;

    /**行情前置机地址*/
    private java.lang.String mdFrontAddr;

    /**交易前置机地址*/
    private java.lang.String tradeFrontAddr;


}

