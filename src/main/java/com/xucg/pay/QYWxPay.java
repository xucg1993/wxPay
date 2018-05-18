package com.xucg.pay;

import com.xucg.model.ResultJson;
import com.xucg.model.WeiXinPrePay;
import com.xucg.model.WeiXinRedEnvelopeModel;
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
    public static String transfers(WeiXinPrePay weixinPrePay, String filePath) throws Exception {

        try {
            //签名算法计算得出的签名值
            weixinPrePay.setSign(WxFormatParamUtil.buildSignStr(weixinPrePay));

            //申请退款XML
            String payXml = WxFormatParamUtil.buildPayXml(weixinPrePay);

            String result = ClientCustomSSL.request(TRANSFERS_URL, weixinPrePay.getqMchId(), payXml, filePath);

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
}
