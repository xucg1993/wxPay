package com.xucg.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xucg.config.WxPayConfigEnum;
import com.xucg.model.ResultJson;
import com.xucg.model.WxPayPrePayModel;
import com.xucg.model.WxPayResultModel;
import com.xucg.util.aes.AesUtil;
import com.xucg.util.encode.EncodeUtil;
import com.xucg.util.http.HttpUtil;
import com.xucg.util.request.RequestUtil;
import com.xucg.util.string.StringUtil;
import com.xucg.util.wx.ClientCustomSSL;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;
import com.xucg.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * @author xucg
 * @since 2018/3/19
 */
@Component
public class WxPay extends Base {

    private static final Logger logger = LoggerFactory.getLogger("wxPay sdk");

    /**
     * 微信小程序预支付
     *
     * @param model
     * @return
     */
    public String miniAppPay(WxPayPrePayModel model) {

        try {
            model = setWeiXinPrePay(model);

            //交易类型   JsAPI
            model.setTradeType(WxPayConfigEnum.WXPAY_JSAPI.getValue());

            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);
            logger.info(JSONObject.toJSONString(model));
            //请求统一下单接口
            String result = HttpUtil.post(WxPayConfigEnum.UNIFIEDORDER_URL.getValue(), payXml);
            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            Map map = buildMap(xmlValue, model.getPayKey());
            logger.info("微信小程序预支付结果: " + map);
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(map);
            }
            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信小程序支付: 系统错误");
        return ResultJson.getResultJsonError();
    }

    /**
     * H5 支付
     *
     * @param weixinPrePay
     * @return
     */
    public String wapPay(WxPayPrePayModel weixinPrePay) {

        try {
            weixinPrePay = setWeiXinPrePay(weixinPrePay);

            weixinPrePay.setTradeType(WxPayConfigEnum.WXPAY_MWEB.getValue());

            //签名算法计算得出的签名值
            weixinPrePay.setSign(WxPayPrePayModel.buildSignStr(weixinPrePay));

            String payXml = WxPayPrePayModel.buildPayXml(weixinPrePay);

            //请求统一下单接口
            String result = HttpUtil.post(WxPayConfigEnum.UNIFIEDORDER_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            logger.info("微信H5预支付结果: " + xmlValue);
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(xmlValue);
            }

            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信H5支付: 系统错误");
        return ResultJson.getResultJsonError();
    }

    /**
     * 公众号 支付
     *
     * @param model
     * @return
     */
    public String mpPay(WxPayPrePayModel model) {

        try {
            model = setWeiXinPrePay(model);

            model.setTradeType(WxPayConfigEnum.WXPAY_JSAPI.getValue());

            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);

            //请求统一下单接口
            String result = HttpUtil.post(WxPayConfigEnum.UNIFIEDORDER_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            logger.info("微信公众号预支付结果: " + xmlValue);
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(xmlValue);
            }

            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信公众号支付: 系统错误");
        return ResultJson.getResultJsonError();
    }

    /**
     * 二维码 支付
     *
     * @param model
     * @return
     */
    public String scanCodePay(WxPayPrePayModel model) {

        try {
            model = setWeiXinPrePay(model);

            //交易类型
            model.setTradeType(WxPayConfigEnum.WXPAY_NATIVE.getValue());

            //用户标识
            model.setOpenId(null);

            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);

            String result = HttpUtil.post(WxPayConfigEnum.UNIFIEDORDER_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            logger.info("微信二维码预支付结果: " + xmlValue);
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {

                return ResultJson.getResultJsonSuccess(xmlValue);

            }
            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信二维码支付: 系统错误");
        return ResultJson.getResultJsonError();
    }


    /**
     * APP 支付
     *
     * @param model
     * @return
     */
    public String appPay(WxPayPrePayModel model) {

        try {
            model = setWeiXinPrePay(model);
            //支付类型
            model.setTradeType(WxPayConfigEnum.WXPAY_APP.getValue());

            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);

            //请求统一下单接口
            String result = HttpUtil.post(WxPayConfigEnum.UNIFIEDORDER_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);

            logger.info("微信APP预支付结果: " + xmlValue);

            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(xmlValue);
            }
            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信支付 APP 支付: 系统错误");
        return ResultJson.getResultJsonError();
    }


    /**
     * 小程序支付 加密paySign
     *
     * @param map
     * @return
     */
    public static Map buildMap(Map<String, String> map, String payKey) throws Exception {
        long date = Calendar.getInstance().getTimeInMillis() / 1000;

        map.put("prepay_id", "prepay_id=" + map.get("prepay_id"));
        StringBuilder sb = new StringBuilder();
        sb.append("appId=").append(map.get("appid"));
        sb.append("&nonceStr=").append(map.get("nonce_str"));
        sb.append("&package=").append(map.get("prepay_id"));
        sb.append("&signType=").append("MD5");
        sb.append("&timeStamp=").append(date);
        sb.append("&key=").append(payKey);

        map.put("paySign", WXPayUtil.MD5(sb.toString()));
        map.put("timeStamp", String.valueOf(date));
        return map;
    }


    /**
     * 支付回调
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public String callback(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //读取参数
            String xmlData = RequestUtil.getRequestBody(request);

            //判断参数是否为空
            if (StringUtil.isNullorEmpty(xmlData)) {
                //通知失败
                response.getWriter().println(WxFormatParamUtil.resultFail());

                return null;
            }
            //将xml转成 实体类
            WxPayResultModel result = (WxPayResultModel) XmlUtil.convertXmlStrToObject(xmlData, WxPayResultModel.class);

            //判断业务
            if (WxFormatParamUtil.isPayReturnSuccess(result)) {
                //支付成功
                logger.info("微信支付回调: 成功" + xmlData);
                return ResultJson.getResultJsonSuccess(result);

            } else {
                //支付失败
                logger.info("微信支付回调: 失败" + xmlData);
                response.getWriter().println(WxFormatParamUtil.resultFail());

                return ResultJson.getResultJsonFail(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("微信支付支付回调: 系统错误");
        return ResultJson.getResultJsonError();
    }

    /**
     * 查询退款
     *
     * @param model
     * @return
     */
    public String refundQuery(WxPayPrePayModel model) {
        try {
            model = setWeiXinPrePay(model);

            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);

            String result = HttpUtil.post(WxPayConfigEnum.REFUNDQUERY_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            String errCode = xmlValue.get(WxPayConfigEnum.ERR_CODE.getValue());
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                logger.info("微信支付查询退款: 成功" + result);
                return ResultJson.getResultJsonSuccess(xmlValue);

            } else if (SYSTEMERROR.equals(errCode)) {
                // 	接口返回错误	系统超时
                logger.info("微信支付查询退款: 系统超时" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_2, SYSTEMERROR, xmlValue);

            } else if (REFUNDNOTEXIST.equals(errCode)) {
                //	退款订单查询失败	订单号错误或订单状态不正确
                //  请检查订单号是否有误以及订单状态是否正确，如：未支付、已支付未退款
                logger.info("微信支付查询退款: 查询失败" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_3, REFUNDNOTEXIST, xmlValue);

            } else if (INVALID_TRANSACTIONID.equals(errCode)) {
                //无效transaction_id	请求参数未按指引进行填写
                logger.info("微信支付查询退款: 无效transaction_id" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_4, INVALID_TRANSACTIONID, xmlValue);

            } else if (PARAM_ERROR.equals(errCode)) {
                //参数错误	请求参数未按指引进行填写
                logger.info("微信支付查询退款: 参数错误" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_5, PARAM_ERROR, xmlValue);

            } else if (APPID_NOT_EXIST.equals(errCode)) {
                //APPID不存在
                logger.info("微信支付查询退款: APPID不存在" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_6, APPID_NOT_EXIST, xmlValue);

            } else if (MCHID_NOT_EXIST.equals(errCode)) {
                //MCHID不存在
                logger.info("微信支付查询退款: MCHID不存在" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_7, MCHID_NOT_EXIST, xmlValue);

            } else if (REQUIRE_POST_METHOD.equals(errCode)) {
                //	请使用post方法
                logger.info("微信支付查询退款: 请使用post方法" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_8, REQUIRE_POST_METHOD, xmlValue);

            } else if (SIGNERROR.equals(errCode)) {
                //签名错误
                logger.info("微信支付查询退款: 签名错误" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_9, SIGNERROR, xmlValue);

            } else if (XML_FORMAT_ERROR.equals(errCode)) {
                //	XML格式错误
                logger.info("微信支付查询退款: XML格式错误" + result);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_10, XML_FORMAT_ERROR, xmlValue);
            }

            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信支付查询退款: 系统错误");
        return ResultJson.getResultJsonError();
    }

    /**
     * 申请退款
     *
     * @param model
     * @param filePath
     * @return
     * @throws Exception
     */
    public String refund(WxPayPrePayModel model, String filePath) throws Exception {

        model = setWeiXinPrePay(model);

        //签名算法计算得出的签名值
        model.setSign(WxPayPrePayModel.buildSignStr(model));

        //申请退款XML
        String payXml = WxPayPrePayModel.buildPayXml(model);

        //请求微信退款接口
        String result = ClientCustomSSL.request(WxPayConfigEnum.REFUND_URL.getValue(), model.getMchId(), payXml, filePath);

        //申请退款结果
        Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
        logger.info("微信申请退款结果: " + xmlValue);
        //判断结果
        if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
            return ResultJson.getResultJsonSuccess(xmlValue);
        }
        return ResultJson.getResultJsonFail(xmlValue);
    }


    /**
     * 退款结果
     * 通知回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public String refundCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //读取参数
        String xmlData = RequestUtil.getRequestBody(request);

        try {
            //解析XML成对象
            WxPayResultModel weiXinPayResult = (WxPayResultModel) XmlUtil.convertXmlStrToObject(xmlData, WxPayResultModel.class);

            //判断微信服务器通讯结果  是否成功
            if (WxPayConfigEnum.SUCCESS.getValue().equals(weiXinPayResult.getReturn_code())) {


                //获取加密信息
                String reqInfo = weiXinPayResult.getReq_info();

                //MD5加密后的key
                String key = EncodeUtil.getMD5For32UTF8("");

                //Aes 解密
                String decrypt = AesUtil.decrypt(reqInfo, key);

                //转成map格式
                Map<String, String> xmlValue = XmlUtil.getXmlValue(decrypt);

                //业务通知字段
                String refundCode = xmlValue.get(WxPayConfigEnum.REFUND_CODE.getValue());

                weiXinPayResult = JSON.parseObject(xmlValue.toString(), WxPayResultModel.class);

                //判断业务通知字段
                if (WxPayConfigEnum.SUCCESS.getValue().equals(refundCode)) {
                    logger.info("微信申请退款结果: 退款成功" + xmlValue);
                    return ResultJson.getResultJsonSuccess(weiXinPayResult);

                } else if (WxPayConfigEnum.CHANGE.getValue().equals(refundCode)) {

                    //退款异常   返回通知失败
                    response.getWriter().println(WxFormatParamUtil.resultFail());

                    logger.info("微信申请退款结果: 退款异常" + xmlValue);

                    return ResultJson.getResultJson(ResultJson.SUCCESS_TRUE, ResultJson.CODE_2, "CHANGE", weiXinPayResult);

                } else if (WxPayConfigEnum.REFUNDCLOSE.getValue().equals(refundCode)) {

                    //退款关闭   返回通知失败
                    response.getWriter().println(WxFormatParamUtil.resultFail());

                    logger.info("微信申请退款结果: 退款关闭" + xmlValue);

                    return ResultJson.getResultJson(ResultJson.SUCCESS_TRUE, ResultJson.CODE_3, "REFUNDCLOSE", weiXinPayResult);

                }

            } else {
                //接收微信服务器通知失败
                response.getWriter().println(WxFormatParamUtil.resultFail());
                logger.info("微信申请退款结果: 接收微信服务器通知失败");
                return ResultJson.getResultJsonFail(weiXinPayResult);
            }
        } catch (Exception e) {
            //系统错误
            e.printStackTrace();
        }
        response.getWriter().println(WxFormatParamUtil.resultFail());
        logger.info("微信申请退款结果: 系统错误");
        return ResultJson.getResultJsonError();
    }


    /**
     * 查询
     * out_trade_no 商户订单号
     *
     * @return
     */
    public String query(WxPayPrePayModel model) {
        try {
            //签名算法计算得出的签名值
            model.setSign(WxPayPrePayModel.buildSignStr(model));

            //将预支付对象封装成Xml
            String payXml = WxPayPrePayModel.buildPayXml(model);

            //请求接口
            String result = HttpUtil.post(WxPayConfigEnum.ORDERQUERY_URL.getValue(), payXml);

            //返回结果
            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);

            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {

                //交易状态
                String tradeState = xmlValue.get(WxPayConfigEnum.TRADE_STATE.getValue());

                if (SUCCESS.equals(tradeState)) {
                    //return_code 、result_code、trade_state都为SUCCESS时有返回
                    //支付成功
                    logger.info("微信支付查询结果: 支付成功" + xmlValue);
                    return ResultJson.getResultJsonSuccess(xmlValue);
                }

                if (REFUND.equals(tradeState)) {
                    //转入退款
                    logger.info("微信支付查询结果: 转入退款" + xmlValue);
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_2, REFUND, xmlValue);
                }

                if (NOTPAY.equals(tradeState)) {
                    //未支付
                    logger.info("微信支付查询结果: 未支付" + xmlValue);
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_3, NOTPAY, xmlValue);
                }

                if (CLOSED.equals(tradeState)) {
                    //已关闭
                    logger.info("微信支付查询结果: 已关闭" + xmlValue);
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_4, CLOSED, xmlValue);
                }

                if (REVOKED.equals(tradeState)) {
                    //已撤销（刷卡支付）
                    logger.info("微信支付查询结果: 已撤销" + xmlValue);
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_5, REVOKED, xmlValue);
                }

                if (USERPAYING.equals(tradeState)) {
                    //用户支付中
                    logger.info("微信支付查询结果: 用户支付中" + xmlValue);
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_6, USERPAYING, xmlValue);
                }

                if (PAYERROR.equals(tradeState)) {
                    //支付失败(其他原因，如银行返回失败)
                    return ResultJson.getResultJsonSuccess(ResultJson.CODE_7, PAYERROR, xmlValue);
                }
            }
            return ResultJson.getResultJsonFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //系统错误
        logger.info("微信支付查询: 系统错误");
        return ResultJson.getResultJsonError();
    }


    /**
     * 关闭订单
     * out_trade_no 商户订单号
     *
     * @param model
     */
    public String close(WxPayPrePayModel model) {
        //签名算法计算得出的签名值
        try {

            model = setWeiXinPrePay(model);

            model.setSign(WxPayPrePayModel.buildSignStr(model));

            String payXml = WxPayPrePayModel.buildPayXml(model);

            String result = HttpUtil.post(WxPayConfigEnum.CLOSEORDER_URL.getValue(), payXml);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);

            String errCode = xmlValue.get(WxPayConfigEnum.ERR_CODE.getValue());

            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                logger.info("微信支付关闭订单结果: 关闭成功" + xmlValue);
                return ResultJson.getResultJsonSuccess(xmlValue);

            } else if (ORDERPAID.equals(errCode)) {

                //订单已支付
                logger.info("微信支付关闭订单结果: 已支付" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_2, ORDERPAID, xmlValue);

            } else if (SYSTEMERROR.equals(errCode)) {

                //系统错误
                logger.info("微信支付关闭订单结果: 系统错误" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_3, SYSTEMERROR, xmlValue);

            } else if (ORDERCLOSED.equals(errCode)) {

                //订单已关闭
                logger.info("微信支付关闭订单结果: 已关闭" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_4, ORDERCLOSED, xmlValue);

            } else if (SIGNERROR.equals(errCode)) {

                //签名错误
                logger.info("微信支付关闭订单结果: 签名错误" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_5, SIGNERROR, xmlValue);

            } else if (REQUIRE_POST_METHOD.equals(errCode)) {

                //请使用post方法
                logger.info("微信支付关闭订单结果: 请使用post方法" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_6, REQUIRE_POST_METHOD, xmlValue);

            } else if (XML_FORMAT_ERROR.equals(errCode)) {

                //XML格式错误
                logger.info("微信支付关闭订单结果: XML格式错误" + xmlValue);
                return ResultJson.getResultJsonSuccess(ResultJson.CODE_7, XML_FORMAT_ERROR, xmlValue);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //系统错误
        logger.info("微信支付关闭订单: 系统错误");
        return ResultJson.getResultJsonError();
    }


    public static void main(String[] args) {
        String key = EncodeUtil.getMD5For32UTF8("H68LB0G5CyeFArcYleQh93GYJP6U2a8X");

        System.out.println(key);
    }
}
