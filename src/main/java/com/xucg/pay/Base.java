package com.xucg.pay;

import com.alibaba.fastjson.JSONObject;
import com.xucg.config.WxPayConfigService;
import com.xucg.model.WxPayMicroModel;
import com.xucg.model.WxPayPrePayModel;
import com.xucg.model.WxPayTransferDibModel;
import com.xucg.util.wx.WxFormatParamUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author xuchenguang
 * @since 2019.01.03
 */
public class Base {

    /*----------------------------关闭订单-----------------------------*/
    /**
     * 订单已支付
     */
    static final String ORDERPAID = "ORDERPAID";
    /**
     * 系统错误
     */
    static final String SYSTEMERROR = "SYSTEMERROR";
    /**
     * 订单已关闭
     */
    static final String ORDERCLOSED = "ORDERCLOSED";
    /**
     * 签名错误
     */
    static final String SIGNERROR = "SIGNERROR";
    /**
     * 请使用post方法
     */
    static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";
    /**
     * XML格式错误
     */
    static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";

    /*-----------------------------查询-----------------------------*/
    /**
     * 支付成功
     */
    static final String SUCCESS = "SUCCESS";
    /**
     * 转入退款
     */
    static final String REFUND = "REFUND";
    /**
     * 未支付
     */
    static final String NOTPAY = "NOTPAY";
    /**
     * 已关闭
     */
    static final String CLOSED = "CLOSED";
    /**
     * 已撤销（刷卡支付）
     */
    static final String REVOKED = "REVOKED";
    /**
     * 用户支付中
     */
    static final String USERPAYING = "USERPAYING";
    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    static final String PAYERROR = "PAYERROR";

    /*-----------------------------查询退款---------------------------------*/
    static final String REFUNDNOTEXIST = "REFUNDNOTEXIST";
    static final String INVALID_TRANSACTIONID = "INVALID_TRANSACTIONID";
    static final String PARAM_ERROR = "PARAM_ERROR";
    static final String APPID_NOT_EXIST = "APPID_NOT_EXIST";
    static final String MCHID_NOT_EXIST = "MCHID_NOT_EXIST";

    @Autowired
    WxPayConfigService wxPayConfigService;

    /**
     * 微信预支付
     *
     * @param model
     * @return
     */
    WxPayPrePayModel setWeiXinPrePay(WxPayPrePayModel model) {
        model.setAppId(wxPayConfigService.getAppId());
        model.setMchId(wxPayConfigService.getMchId());
        model.setPayKey(wxPayConfigService.getPayKey());
        model.setNonceStr(WxFormatParamUtil.getNonceStr());
        return model;
    }

    /**
     * 刷卡支付
     *
     * @param model
     * @return
     */
    WxPayMicroModel setWxPayMicroModel(WxPayMicroModel model) {
        model.setAppId(wxPayConfigService.getAppId());
        model.setMchId(wxPayConfigService.getMchId());
        model.setPayKey(wxPayConfigService.getPayKey());
        model.setNonceStr(WxFormatParamUtil.getNonceStr());
        return model;
    }

    /**
     * 转账到用户零钱
     *
     * @param model
     * @return
     */
    WxPayTransferDibModel setWxPayTransferDibModel(WxPayTransferDibModel model) {
        model.setMchAppId(wxPayConfigService.getAppId());
        model.setMchId(wxPayConfigService.getMchId());
        model.setPayKey(wxPayConfigService.getPayKey());
        model.setNonceStr(WxFormatParamUtil.getNonceStr());
        return model;
    }

/*    public <T> T buildPayConfig(Class<T> clazz) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appId", wxPayConfigService.getAppId());
        map.put("mchId", wxPayConfigService.getMchId());
        map.put("payKey", wxPayConfigService.getPayKey());
        map.put("nonceStr", WxFormatParamUtil.getNonceStr());
        String string = JSONObject.toJSONString(map);
        return JSONObject.parseObject(string, clazz);
    }*/

}