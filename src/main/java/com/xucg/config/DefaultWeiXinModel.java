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
    /**
     * 默认配置
     *
     * @return
     */
    public static WxPayPrePayModel getDefaultWeiXinModel() {
        return new WxPayPrePayModel(appId, mchId, payKey);
    }
}
