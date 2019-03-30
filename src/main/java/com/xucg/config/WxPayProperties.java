package com.xucg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuchenguang
 * @since 2019.01.02
 */
@ConfigurationProperties(prefix = WxPayProperties.WX_PAY_PREFIX)
public class WxPayProperties {

    public static final String WX_PAY_PREFIX = "wxpay";

    private String appId;
    private String mchId;
    private String payKey;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }
}
