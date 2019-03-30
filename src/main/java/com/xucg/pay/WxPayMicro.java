package com.xucg.pay;

import com.xucg.config.WxPayConfigEnum;
import com.xucg.model.ResultJson;
import com.xucg.model.WxPayMicroModel;
import com.xucg.util.http.HttpUtil;
import com.xucg.util.wx.WxFormatParamUtil;
import com.xucg.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 刷卡支付
 *
 * @author xuchenguang
 * @date 2018.05.24
 */
@Component
public class WxPayMicro extends Base {

    private static final Logger logger = LoggerFactory.getLogger("wxPay sdk");

    /**
     * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
     *
     * @param model
     * @return
     */
    public String request(WxPayMicroModel model) throws Exception {

        model = setWxPayMicroModel(model);
        //签名算法计算得出的签名值
        model.setSign(WxPayMicroModel.buildSignStr(model));

        String payXml = WxPayMicroModel.buildPayXml(model);

        //请求统一下单接口
        String result = HttpUtil.post(WxPayConfigEnum.MICRO_PAY_URL.getValue(), payXml);

        Map<String, String> xmlValue = XmlUtil.getXmlValue(result);

        logger.info("微信刷卡支付支付结果: " + xmlValue);

        if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
            return ResultJson.getResultJsonSuccess(xmlValue);
        }
        return ResultJson.getResultJsonFail(xmlValue);

    }
}
