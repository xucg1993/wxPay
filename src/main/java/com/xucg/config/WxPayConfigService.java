package com.xucg.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author xuchenguang
 * @since 2019.01.02
 */
public class WxPayConfigService {

    private static final Logger logger = LoggerFactory.getLogger(WxPayConfigService.class);

    private String appId;
    private String mchId;
    private String payKey;

    private HashMap<String, Object> storage = new HashMap<String, Object>();

    public WxPayConfigService(WxPayProperties properties) {
        super();
        this.appId = properties.getAppId();
        this.mchId = properties.getMchId();
        this.payKey = properties.getPayKey();
        logger.info("appId:" + appId);
        logger.info("mchId:" + mchId);
        logger.info("payKey:" + payKey);
    }


    public void put(String key, Object val) {
        storage.put(key, val);
    }

    public Object get(String key) {
        return storage.get(key);
    }

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
