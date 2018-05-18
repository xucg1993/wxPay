package com.xucg.model;

import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信红包
 *
 * @author xuchenguang
 * @date 2018.05.18
 */
public class WeiXinRedEnvelopeModel {

    /**
     * 随机字符串*
     */
    private String nonceStr;

    /**
     * 签名*
     */
    private String sign;

    /**
     * 商户订单号*
     */
    private String mchBillno;

    /**
     * 商户号*
     */
    private String mchId;

    /**
     * 子商户号	~* 服务商模式下必填
     */
    private String subMchId;

    /**
     * 公众账号appid *
     */
    private String wxAppId;

    /**
     * 触达用户appid *
     */
    private String msgAppId;

    /**
     * 商户名称
     */
    private String sendName;

    /**
     * 用户openid
     */
    private String reOpenid;

    /**
     * 付款金额
     */
    private Integer totalAmount;

    /**
     * 红包发放总人数
     */
    private Integer totalNum;

    /**
     * 红包祝福语
     */
    private String wishing;

    /**
     * Ip地址
     */
    private String clientIp;

    /**
     * 活动名称
     */
    private String actName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 场景id
     */
    private String sceneId;

    /**
     * 活动信息
     */
    private String riskInfo;

    /**
     * 扣钱方mchid
     */
    private String consumeMchId;

    /**
     * 支付KEY
     */
    private String payKey;

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

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getMsgAppId() {
        return msgAppId;
    }

    public void setMsgAppId(String msgAppId) {
        this.msgAppId = msgAppId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(String riskInfo) {
        this.riskInfo = riskInfo;
    }

    public String getConsumeMchId() {
        return consumeMchId;
    }

    public void setConsumeMchId(String consumeMchId) {
        this.consumeMchId = consumeMchId;
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
     * @param redEnvelopeModel
     * @return
     */
    public static Map<String, Object> buildWeiXinRedEnvelopeModelToMap(WeiXinRedEnvelopeModel redEnvelopeModel) {

        Map<String, Object> data = new HashMap<>(16);

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getNonceStr())) {
            data.put("nonce_str", redEnvelopeModel.getNonceStr());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getSign())) {
            data.put("sign", redEnvelopeModel.getSign());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getMchBillno())) {
            data.put("mch_billno", redEnvelopeModel.getMchBillno());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getMchId())) {
            data.put("mch_id", redEnvelopeModel.getMchId());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getSubMchId())) {
            data.put("sub_mch_id", redEnvelopeModel.getSubMchId());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getWxAppId())) {
            data.put("wxappid", redEnvelopeModel.getWxAppId());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getMsgAppId())) {
            data.put("msgappid", redEnvelopeModel.getMsgAppId());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getSendName())) {
            data.put("send_name", redEnvelopeModel.getSendName());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getReOpenid())) {
            data.put("re_openid", redEnvelopeModel.getReOpenid());
        }

        if (redEnvelopeModel.getTotalAmount() != null) {
            data.put("total_amount", redEnvelopeModel.getTotalAmount());
        }

        if (redEnvelopeModel.getTotalNum() != null) {
            data.put("total_num", redEnvelopeModel.getTotalNum());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getWishing())) {
            data.put("wishing", redEnvelopeModel.getWishing());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getClientIp())) {
            data.put("client_ip", redEnvelopeModel.getClientIp());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getActName())) {
            data.put("act_name", redEnvelopeModel.getActName());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getRemark())) {
            data.put("remark", redEnvelopeModel.getRemark());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getSceneId())) {
            data.put("scene_id", redEnvelopeModel.getSceneId());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getRiskInfo())) {
            data.put("risk_info", redEnvelopeModel.getRiskInfo());
        }

        if (!StringUtil.isNullorEmpty(redEnvelopeModel.getConsumeMchId())) {
            data.put("consume_mch_id", redEnvelopeModel.getConsumeMchId());
        }

        return data;
    }

    /**
     * 微信红包
     * 加密sign之前 将参数拼接成字符串并加密
     *
     * @param pay
     * @return
     */
    public static String buildSignStr(WeiXinRedEnvelopeModel pay) throws Exception {
        return WXPayUtil.generateSignature(buildWeiXinRedEnvelopeModelToMap(pay), pay.getPayKey());
    }

    /**
     * 微信红包
     * 拼接Xml格式
     *
     * @param pay
     * @return
     */
    public static String buildPayXml(WeiXinRedEnvelopeModel pay) throws Exception {
        return WXPayUtil.mapToXml(buildWeiXinRedEnvelopeModelToMap(pay));
    }


    public WeiXinRedEnvelopeModel(String mchId, String subMchId, String wxAppId, String msgAppId, String payKey) {

        this.setNonceStr(WxFormatParamUtil.getNonceStr());
        this.setMchId(mchId);
        this.setSubMchId(subMchId);
        this.setWxAppId(wxAppId);
        this.setMsgAppId(msgAppId);
        this.setMsgAppId(msgAppId);
        this.setPayKey(payKey);
    }

}
