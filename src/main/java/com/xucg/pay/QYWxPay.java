package com.xucg.pay;

import com.xucg.model.ResultJson;
import com.xucg.model.WeiXinPrePay;
import com.xucg.util.wx.ClientCustomSSL;
import com.xucg.util.wx.WxFormatParamUtil;
import com.xucg.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author xuchenguang
 * @date 2018.05.07
 */
public class QYWxPay {
    private static final Logger logger = LoggerFactory.getLogger("wxPay sdk");

    private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 企业商户向用户微信账户零钱转账
     *
     * @param weixinPrePay
     * @param filePath
     * @return
     * @throws Exception
     */
    private static String transfers(WeiXinPrePay weixinPrePay, String filePath) throws Exception {

        try {
            //签名算法计算得出的签名值
            weixinPrePay.setSign(WxFormatParamUtil.buildSignStr(weixinPrePay));

            //申请退款XML
            String payXml = WxFormatParamUtil.buildPayXml(weixinPrePay);

            String result = ClientCustomSSL.request(TRANSFERS_URL, weixinPrePay.getMchId(), payXml, filePath);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            logger.info("微信转账结果" + xmlValue);
            //判断结果
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(xmlValue);
            }
            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信转账结果 : 系统异常");
        return ResultJson.getResultJsonError();
    }

    public static void main(String[] args) {
        WeiXinPrePay prePay = new WeiXinPrePay(null, "1486668342", "8WOyzGdw5AKjtqUodOVKQ2mlxse1Q1qZ");
        prePay.setMchAppId(prePay.getAppid());
        prePay.setOpenId("o6iPM4rL6rkaK4rHHh7uxRdxa9wM");
        prePay.setPartnerTradeNo("20181123100312");
        prePay.setCheckName("FORCE_CHECK");
        prePay.setReUserName("徐晨光");
        prePay.setAmount(100);
        prePay.setDesc("测试");
        prePay.setSpbillCreateIp("127.0.0.1");
        String s = "";
        try {
            s = transfers(prePay, "C:\\Users\\lili\\Desktop\\hanchu_apiclient_cert.p12");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
