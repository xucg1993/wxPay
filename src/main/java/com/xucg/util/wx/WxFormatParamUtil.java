package com.xucg.util.wx;

import com.xucg.config.WxPayConfigEnum;
import com.xucg.model.WeiXinPayResult;
import com.xucg.model.WeiXinPrePay;
import com.xucg.util.string.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xucg
 * @since 2018/2/26
 */
public class WxFormatParamUtil {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    /**
     * 获取10位的Nonc
     *
     * @return
     */
    public static String getNonceStr() {
        String randomStringAndNumber = StringUtil.getRandomStringAndNumber(16);
        return randomStringAndNumber;
    }

    /**
     * 微信支付
     * 加密sign之前 将参数拼接成字符串并加密
     *
     * @param pay
     * @return
     */
    public static String buildSignStr(WeiXinPrePay pay) throws Exception {
        return WXPayUtil.generateSignature(buildWeiXinPrePayToMap(pay), pay.getPayKey());
    }

    /**
     * 微信支付
     * 拼接Xml格式
     *
     * @param pay
     * @return
     */
    public static String buildPayXml(WeiXinPrePay pay) throws Exception {
        return WXPayUtil.mapToXml(buildWeiXinPrePayToMap(pay));
    }

    /**
     * return_code 和 result_code 都是SUCCESS，才是成功
     */
    public static boolean isPayReturnSuccess(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String successValue = WxPayConfigEnum.SUCCESS.getValue();
        String returnCodeValue = WxPayConfigEnum.RETURN_CODE.getValue();
        String resultCodeValue = WxPayConfigEnum.RESULT_CODE.getValue();
        return successValue.equalsIgnoreCase(map.get(returnCodeValue)) && successValue.equalsIgnoreCase(map.get(resultCodeValue));
    }

    public static boolean isPayReturnSuccess(WeiXinPayResult result) {
        if (result == null) {
            return false;
        } else {
            String successValue = WxPayConfigEnum.SUCCESS.getValue();
            return successValue.equalsIgnoreCase(result.getReturn_code()) && successValue.equalsIgnoreCase(result.getResult_code());
        }
    }

    /**
     * 成功
     *
     * @return
     */
    public static String resultSuccess() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml><return_code><![CDATA[" + SUCCESS + "]]></return_code><return_msg><![CDATA[" + OK + "]]></return_msg></xml>");
        return sb.toString();
    }

    /**
     * 失败
     *
     * @return
     */
    public static String resultFail() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml><return_code><![CDATA[" + FAIL + "]]></return_code><return_msg><![CDATA[" + ERROR + "]]></return_msg></xml>");
        return sb.toString();
    }

    /**
     * 自定义
     *
     * @param code
     * @param msg
     * @return
     */
    public static String resultCustom(String code, String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml><return_code><![CDATA[" + code + "]]></return_code><return_msg><![CDATA[" + msg + "]]></return_msg></xml>");
        return sb.toString();
    }

    /**
     * 将实体类封装成map
     *
     * @param pay
     * @return
     */
    public static Map<String, Object> buildWeiXinPrePayToMap(WeiXinPrePay pay) {
        Map<String, Object> data = new HashMap<>(16);
        if (!StringUtil.isNullorEmpty(pay.getMchAppId())) {
            data.put("mch_appid", pay.getMchAppId());
        }
        if (!StringUtil.isNullorEmpty(pay.getqMchId())) {
            data.put("mchid", pay.getqMchId());
        }
        //校验用户姓名选项
        if (!StringUtil.isNullorEmpty(pay.getCheckName())) {
            data.put("check_name", pay.getCheckName());
        }
        //收款用户姓名
        if (!StringUtil.isNullorEmpty(pay.getReUserName())) {
            data.put("re_user_name", pay.getReUserName());
        }

        //收款用户姓名
        if (!StringUtil.isNullorEmpty(pay.getDesc())) {
            data.put("desc", pay.getDesc());
        }

        if (pay.getAmount() != null) {
            data.put("amount", pay.getAmount());
        }

        if (!StringUtil.isNullorEmpty(pay.getPartnerTradeNo())) {
            data.put("partner_trade_no", pay.getPartnerTradeNo());
        }

        if (!StringUtil.isNullorEmpty(pay.getAppid())) {
            data.put("appid", pay.getAppid());
        }
        if (!StringUtil.isNullorEmpty(pay.getMchId())) {
            data.put("mch_id", pay.getMchId());
        }
        if (!StringUtil.isNullorEmpty(pay.getNonceStr())) {
            data.put("nonce_str", pay.getNonceStr());
        }
        if (!StringUtil.isNullorEmpty(pay.getNotifyUrl())) {
            data.put("notify_url", pay.getNotifyUrl());
        }
        if (!StringUtil.isNullorEmpty(pay.getBody())) {
            data.put("body", pay.getBody());
        }
        if (!StringUtil.isNullorEmpty(pay.getOutRefundNo())) {
            data.put("out_refund_no", pay.getOutRefundNo());
        }
        if (!StringUtil.isNullorEmpty(pay.getOutTradeNo())) {
            data.put("out_trade_no", pay.getOutTradeNo());
        }
        if (!StringUtil.isNullorEmpty(pay.getOpenId())) {
            data.put("openid", pay.getOpenId());
        }
        if (!StringUtil.isNullorEmpty(pay.getSpbillCreateIp())) {
            data.put("spbill_create_ip", pay.getSpbillCreateIp());
        }
        if (pay.getRefundFee() != null) {
            data.put("refund_fee", pay.getRefundFee());
        }
        if (pay.getTotalFee() != null) {
            data.put("total_fee", pay.getTotalFee());
        }
        if (!StringUtil.isNullorEmpty(pay.getTradeType()) && pay.getTradeType().equals(WxPayConfigEnum.WXPAY_MWEB.getValue())) {
            data.put("scene_info", pay.getSceneInfo());
        }
        if (!StringUtil.isNullorEmpty(pay.getTradeType())) {
            data.put("trade_type", pay.getTradeType());
        }
        if (!StringUtil.isNullorEmpty(pay.getSign())) {
            data.put("sign", pay.getSign());
        }
        return data;
    }
}
