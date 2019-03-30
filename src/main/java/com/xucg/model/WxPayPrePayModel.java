package com.xucg.model;

import com.xucg.config.WxPayConfigEnum;
import com.xucg.config.WxPayConfigService;
import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信预支付
 *
 * @author xucg
 * @since 2017/11/8
 */
public class WxPayPrePayModel {

    /**
     * appid
     */
    private String appId;
    /**
     * 简要描述，订单描述不能是中文，不能是中文
     */
    private String body;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 通知地址：接收微信支付异步通知回调地址
     */
    private String notifyUrl;
    /**
     * 用户的微信id
     */
    private String openId;
    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 商户订单号 (企业向微信用户转账使用)
     */
    private String partnerTradeNo;

    /**
     * 退款号
     */
    private String outRefundNo;

    /**
     * 终端ip: APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
     */
    private String spbillCreateIp;

    /**
     * 总金额，单位：分，整数
     */
    private Integer totalFee;

    /**
     * 退款金额，单位：分，整数
     */
    private Integer refundFee;

    /**
     * 签名
     */
    private String sign;

    /***/
    private String prepayId;

    /***/
    private long timesTamp;

    /**
     * 是否已经支付过了，1：是，0：否
     */
    private int payed;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 二维码链接
     */
    private String codeUrl;

    /**
     * 微信支付分配的商户号
     */
    private String mchId;

    /**
     * 支付密钥
     */
    private String payKey;

    /**
     * 支付类型
     */
    private String tradeType;

    /**
     * 场景信息
     */
    private String sceneInfo;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public long getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(long timesTamp) {
        this.timesTamp = timesTamp;
    }

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    /**
     * 将实体类封装成map
     *
     * @param model
     * @return
     */
    public static Map<String, String> buildToMap(WxPayPrePayModel model) {
        Map<String, String> data = new HashMap<>(16);

        if (!StringUtil.isNullorEmpty(model.getPartnerTradeNo())) {
            data.put("partner_trade_no", model.getPartnerTradeNo());
        }

        if (!StringUtil.isNullorEmpty(model.getAppId())) {
            data.put("appid", model.getAppId());
        }
        if (!StringUtil.isNullorEmpty(model.getMchId())) {
            data.put("mch_id", model.getMchId());
        }
        if (!StringUtil.isNullorEmpty(model.getNonceStr())) {
            data.put("nonce_str", model.getNonceStr());
        }
        if (!StringUtil.isNullorEmpty(model.getNotifyUrl())) {
            data.put("notify_url", model.getNotifyUrl());
        }
        if (!StringUtil.isNullorEmpty(model.getBody())) {
            data.put("body", model.getBody());
        }
        if (!StringUtil.isNullorEmpty(model.getOutRefundNo())) {
            data.put("out_refund_no", model.getOutRefundNo());
        }
        if (!StringUtil.isNullorEmpty(model.getOutTradeNo())) {
            data.put("out_trade_no", model.getOutTradeNo());
        }
        if (!StringUtil.isNullorEmpty(model.getOpenId())) {
            data.put("openid", model.getOpenId());
        }
        if (!StringUtil.isNullorEmpty(model.getSpbillCreateIp())) {
            data.put("spbill_create_ip", model.getSpbillCreateIp());
        }
        if (model.getRefundFee() != null) {
            data.put("refund_fee", model.getRefundFee().toString());
        }
        if (model.getTotalFee() != null) {
            data.put("total_fee", model.getTotalFee().toString());
        }
        if (!StringUtil.isNullorEmpty(model.getTradeType()) && model.getTradeType().equals(WxPayConfigEnum.WXPAY_MWEB.getValue())) {
            data.put("scene_info", model.getSceneInfo());
        }
        if (!StringUtil.isNullorEmpty(model.getTradeType())) {
            data.put("trade_type", model.getTradeType());
        }
        if (!StringUtil.isNullorEmpty(model.getSign())) {
            data.put("sign", model.getSign());
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
    public static String buildSignStr(WxPayPrePayModel pay) throws Exception {
        return WXPayUtil.generateSignature(buildToMap(pay), pay.getPayKey());
    }

    /**
     * 微信支付
     * 拼接Xml格式
     *
     * @param pay
     * @return
     */
    public static String buildPayXml(WxPayPrePayModel pay) throws Exception {
        return WXPayUtil.mapToXml(buildToMap(pay));
    }

    /**
     * 微信默认配置
     *
     * @param appId
     * @param mchId
     * @param payKey
     * @return
     */
    @Autowired
    WxPayConfigService wxPayConfigService;

    public WxPayPrePayModel(WxPayConfigService wxPayConfigService) {
        this.setAppId(wxPayConfigService.getAppId());
        this.setMchId(wxPayConfigService.getMchId());
        this.setPayKey(wxPayConfigService.getPayKey());
        this.setNonceStr(WxFormatParamUtil.getNonceStr());
    }

    public WxPayPrePayModel() {
    }

}
