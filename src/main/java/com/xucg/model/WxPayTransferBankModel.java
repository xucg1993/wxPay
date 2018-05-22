package com.xucg.model;

import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 转账到银行卡
 *
 * @author xuchenguang
 * @date 2018.05.21
 */
public class WxPayTransferBankModel {

    /**
     * 不校验真实姓名
     */
    public static final String NO_CHECK = "NO_CHECK";
    /**
     * 强校验真实姓名
     */
    public static final String FORCE_CHECK = "FORCE_CHECK";


    /**
     * 商户号
     */
    private String mchId;

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
     * 收款方银行卡号
     */
    private String encBankNo;

    /**
     * 收款方用户名
     */
    private String encTrueName;

    /**
     * 收款方开户行
     */
    private String bankCode;

    /**
     * 付款金额
     */
    private Integer amount;

    /**
     * 付款说明
     */
    private String desc;

    /**
     * 商户支付KEY
     */
    private String payKey;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
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

    public String getEncBankNo() {
        return encBankNo;
    }

    public void setEncBankNo(String encBankNo) {
        this.encBankNo = encBankNo;
    }

    public String getEncTrueName() {
        return encTrueName;
    }

    public void setEncTrueName(String encTrueName) {
        this.encTrueName = encTrueName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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
    public static Map<String, Object> buildToMap(WxPayTransferBankModel model) {

        Map<String, Object> data = new HashMap<>(16);

        if (!StringUtil.isNullorEmpty(model.getMchId())) {
            data.put("mch_id", model.getMchId());
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

        if (!StringUtil.isNullorEmpty(model.getEncBankNo())) {
            data.put("enc_bank_no", model.getEncBankNo());
        }

        if (!StringUtil.isNullorEmpty(model.getEncBankNo())) {
            data.put("enc_true_name", model.getEncBankNo());
        }
        if (!StringUtil.isNullorEmpty(model.getBankCode())) {
            data.put("bank_code", model.getBankCode());
        }

        if (model.getAmount() != null) {
            data.put("amount", model.getAmount());
        }

        if (!StringUtil.isNullorEmpty(model.getDesc())) {
            data.put("desc", model.getDesc());
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
    public static String buildSignStr(WxPayTransferBankModel model) throws Exception {
        return WXPayUtil.generateSignature(buildToMap(model), model.getPayKey());
    }

    /**
     * 微信红包
     * 拼接Xml格式
     *
     * @param model
     * @return
     */
    public static String buildPayXml(WxPayTransferBankModel model) throws Exception {
        return WXPayUtil.mapToXml(buildToMap(model));
    }

    public WxPayTransferBankModel(String mchId, String payKey) {
        this.setMchId(mchId);
        this.setPayKey(payKey);
        this.setNonceStr(WxFormatParamUtil.getNonceStr());
    }
}
