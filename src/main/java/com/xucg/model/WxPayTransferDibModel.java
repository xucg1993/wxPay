package com.xucg.model;

import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 转账到用户零钱
 *
 * @author xuchenguang
 * @date 2018.05.21
 */
public class WxPayTransferDibModel {

    /**
     * 不校验真实姓名
     */
    public static final String NO_CHECK = "NO_CHECK";
    /**
     * 强校验真实姓名
     */
    public static final String FORCE_CHECK = "FORCE_CHECK";


    /**
     * 商户账号appid
     */
    private String mchAppId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 设备号
     */
    private String deviceInfo;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 签名
     */
    private String sign;

    /**
     * 商户订单号
     */
    private String partnerTradeNo;

    /**
     * 用户openid
     */
    private String openId;

    /**
     * 校验用户姓名选项
     */
    private String checkName;

    /**
     * 收款用户姓名
     */
    private String reUserName;

    /**
     * 金额
     */
    private Integer amount;

    /**
     * 企业付款描述信息
     */
    private String desc;

    /**
     * Ip地址
     */
    private String spbillCreateIp;

    /**
     * 商户支付KEY
     */
    private String payKey;

    public String getMchAppId() {
        return mchAppId;
    }

    public void setMchAppId(String mchAppId) {
        this.mchAppId = mchAppId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    /**
     * 将实体类封装成map
     *
     * @param model
     * @return
     */
    public static Map<String, Object> buildToMap(WxPayTransferDibModel model) {

        Map<String, Object> data = new HashMap<>(16);

        if (!StringUtil.isNullorEmpty(model.getMchAppId())) {
            data.put("mch_appid", model.getMchAppId());
        }

        if (!StringUtil.isNullorEmpty(model.getMchId())) {
            data.put("mchid", model.getMchId());
        }

        if (!StringUtil.isNullorEmpty(model.getDeviceInfo())) {
            data.put("device_info", model.getDeviceInfo());
        }

        if (!StringUtil.isNullorEmpty(model.getNonceStr())) {
            data.put("nonce_str", model.getNonceStr());
        }

        if (!StringUtil.isNullorEmpty(model.getSign())) {
            data.put("sign", model.getSign());
        }

        if (!StringUtil.isNullorEmpty(model.getPartnerTradeNo())) {
            data.put("partner_trade_no", model.getPartnerTradeNo());
        }

        if (!StringUtil.isNullorEmpty(model.getOpenId())) {
            data.put("openid", model.getOpenId());
        }

        if (!StringUtil.isNullorEmpty(model.getCheckName())) {
            data.put("check_name", model.getCheckName());
        }

        if (!StringUtil.isNullorEmpty(model.getReUserName())) {
            data.put("re_user_name", model.getReUserName());
        }

        if (model.getAmount() != null) {
            data.put("amount", model.getAmount());
        }

        if (!StringUtil.isNullorEmpty(model.getDesc())) {
            data.put("desc", model.getDesc());
        }

        if (!StringUtil.isNullorEmpty(model.getSpbillCreateIp())) {
            data.put("spbill_create_ip", model.getSpbillCreateIp());
        }

        return data;
    }

    /**
     * 微信红包
     * 加密sign之前 将参数拼接成字符串并加密
     *
     * @param model
     * @return
     */
    public static String buildSignStr(WxPayTransferDibModel model) throws Exception {
        return WXPayUtil.generateSignature(buildToMap(model), model.getPayKey());
    }

    /**
     * 微信红包
     * 拼接Xml格式
     *
     * @param model
     * @return
     */
    public static String buildPayXml(WxPayTransferDibModel model) throws Exception {
        return WXPayUtil.mapToXml(buildToMap(model));
    }

    public WxPayTransferDibModel(String mchAppId, String mchId, String payKey) {
        this.setMchAppId(mchAppId);
        this.setMchId(mchId);
        this.setPayKey(payKey);
        this.setNonceStr(WxFormatParamUtil.getNonceStr());
    }
}
