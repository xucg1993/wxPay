package com.xucg.pay;

import com.xucg.model.ResultJson;
import com.xucg.model.WeiXinRedEnvelopeModel;
import com.xucg.util.wx.ClientCustomSSL;
import com.xucg.util.wx.WxFormatParamUtil;
import com.xucg.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 微信红包
 *
 * @author xuchenguang
 * @date 2018.05.18
 */
public class WxPayRedEnvelope {

    /**
     * logger日志
     */
    private static final Logger logger = LoggerFactory.getLogger("wxPay sdk");
    /**
     * 请求Url
     * 需要证书
     * POST
     */
    private final static String REQUEST_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";


    /**
     * @param redEnvelopeModel
     * @param filePath
     * @throws Exception
     */
    public static String request(WeiXinRedEnvelopeModel redEnvelopeModel, String filePath) throws Exception {

        //签名算法计算得出的签名值
        redEnvelopeModel.setSign(WeiXinRedEnvelopeModel.buildSignStr(redEnvelopeModel));

        //申请退款XML
        String payXml = WeiXinRedEnvelopeModel.buildPayXml(redEnvelopeModel);

        String result = ClientCustomSSL.request(REQUEST_URL, redEnvelopeModel.getMchId(), payXml, filePath);

        Map<String, String> xmlValue = XmlUtil.getXmlValue(result);

        logger.info("微信红包结果 : " + xmlValue);

        //判断结果
        if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
            return ResultJson.getResultJsonSuccess(xmlValue);
        }
        return ResultJson.getResultJsonFail(xmlValue);
    }
}
