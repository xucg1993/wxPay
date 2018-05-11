package com.xucg.model;

import com.xucg.util.wx.WxFormatParamUtil;

/**
 * 微信预支付
 *
 * @author xucg
 * @since 2017/11/8
 */
public class WeiXinPrePay {

    /**
     * 商户appId
     */
    private String mchAppId;

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
     * appid
     */
    private String appid;
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

    public String getMchAppId() {
        return mchAppId;
    }

    public void setMchAppId(String mchAppId) {
        this.mchAppId = mchAppId;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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
     * 微信默认配置
     *
     * @param appId
     * @param mchId
     * @param payKey
     * @return
     */
    public WeiXinPrePay(String appId, String mchId, String payKey) {
        this.setAppid(appId);
        this.setMchId(mchId);
        this.setPayKey(payKey);
        this.setNonceStr(WxFormatParamUtil.getNonceStr());
    }
}
