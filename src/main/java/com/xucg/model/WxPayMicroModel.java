package com.xucg.model;

import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchenguang
 * @date 2018.05.24
 */
public class WxPayMicroModel {

    /**
     * 公众账号ID
     */
    private String appId;
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
     * 签名类型
     */
    private String signType;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 订单金额
     */
    private Integer totalFee;
    /**
     * 货币类型
     */
    private String feeType;
    /**
     * 终端IP
     */
    private String spbillCreateIp;
    /**
     * 订单优惠标记
     */
    private String goodsTag;
    /**
     * 指定支付方式
     * no_credit--指定不能使用信用卡支付
     */
    private String limitPay;
    /**
     * 交易起始时间
     */
    private String timeStart;
    /**
     * 交易结束时间
     */
    private String timeExpire;
    /**
     * 扫码支付授权码
     */
    private String authCode;
    /**
     * 场景信息
     */
    private String sceneInfo;
    /**
     * 支付密钥
     */
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

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
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
    public static Map<String, String> buildToMap(WxPayMicroModel model) {
        Map<String, String> data = new HashMap<>(16);

        if (!StringUtil.isNullorEmpty(model.getAppId())) {
            data.put("appid", model.getAppId());
        }

        if (!StringUtil.isNullorEmpty(model.getMchId())) {
            data.put("mch_id", model.getMchId());
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

        if (!StringUtil.isNullorEmpty(model.getSceneInfo())) {
            data.put("sign_type", model.getSceneInfo());
        }

        if (!StringUtil.isNullorEmpty(model.getBody())) {
            data.put("body", model.getBody());
        }

        if (!StringUtil.isNullorEmpty(model.getDetail())) {
            data.put("detail", model.getDetail());
        }

        if (!StringUtil.isNullorEmpty(model.getAttach())) {
            data.put("attach", model.getAttach());
        }

        if (!StringUtil.isNullorEmpty(model.getOutTradeNo())) {
            data.put("out_trade_no", model.getOutTradeNo());
        }

        if (model.getTotalFee() != null) {
            data.put("total_fee", model.getTotalFee().toString());
        }

        if (!StringUtil.isNullorEmpty(model.getFeeType())) {
            data.put("fee_type", model.getFeeType());
        }

        if (!StringUtil.isNullorEmpty(model.getSpbillCreateIp())) {
            data.put("spbill_create_ip", model.getSpbillCreateIp());
        }

        if (!StringUtil.isNullorEmpty(model.getGoodsTag())) {
            data.put("goods_tag", model.getGoodsTag());
        }

        if (!StringUtil.isNullorEmpty(model.getLimitPay())) {
            data.put("limit_pay", model.getLimitPay());
        }

        if (!StringUtil.isNullorEmpty(model.getTimeStart())) {
            data.put("time_start", model.getTimeStart());
        }

        if (!StringUtil.isNullorEmpty(model.getTimeExpire())) {
            data.put("time_expire", model.getTimeExpire());
        }

        if (!StringUtil.isNullorEmpty(model.getAuthCode())) {
            data.put("auth_code", model.getAuthCode());
        }

        if (!StringUtil.isNullorEmpty(model.getSceneInfo())) {
            data.put("scene_info", model.getSceneInfo());
        }


        return data;
    }


    /**
     * 微信支付
     * 加密sign之前 将参数拼接成字符串并加密
     *
     * @param pay
     * @return
     */
    public static String buildSignStr(WxPayMicroModel pay) throws Exception {
        return WXPayUtil.generateSignature(buildToMap(pay), pay.getPayKey());
    }

    /**
     * 微信支付
     * 拼接Xml格式
     *
     * @param pay
     * @return
     */
    public static String buildPayXml(WxPayMicroModel pay) throws Exception {
        return WXPayUtil.mapToXml(buildToMap(pay));
    }

}
