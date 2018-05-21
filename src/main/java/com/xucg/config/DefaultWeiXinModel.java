package com.xucg.config;

import com.xucg.model.WxPayPrePayModel;

/**
 * 微信支付
 * 默认配置
 *
 * @author xucg
 * @since 2017/11/8
 */
public class DefaultWeiXinModel {
    private static final String appId = "wxb24d7e2ad00d103b";
    private static final String mchId = "1502029451";
    private static final String payKey = "WRYEC0EgrjGdbxU5AVZBRpYQ7r95Dmc9";
    /**
     * 默认配置
     *
     * @return
     */
    public static WxPayPrePayModel getDefaultWeiXinModel() {
        return new WxPayPrePayModel(appId, mchId, payKey);
    }
}
