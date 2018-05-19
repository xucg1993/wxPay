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

    //
    public static void main(String[] args) {
        WeiXinRedEnvelopeModel model = new WeiXinRedEnvelopeModel("1491328582",//犁里服务商户号
                "1502029451",//水站子商户号
                "wxd79ceb5d2f034a68",//犁里公众号appid
                "wxc66d3d0c1e281982",//水站appid
                "2oeTUVFrBRrHaW0I6Bz4On88d511OAT4");//犁里服务号支付密钥
        model.setMchBillno("20180514092741052000001");
        //商户名称
        model.setSendName("测试");
        //用户openid
        model.setReOpenid("ozKxd1vn0BPcsjaK5zBhBNq4Mu_w");
        //付款金额
        model.setTotalAmount(100);
        //红包发放总人数
        model.setTotalNum(1);
        //红包祝福语
        model.setWishing("大吉大利晚上吃鸡");
        //Ip地址
        model.setClientIp("123.54.16.7");
        //活动名称
        model.setActName("吃鸡奖励");
        //备注
        model.setRemark("呵呵");
        //场景id
//        model.setSceneId("PRODUCT_5");
        try {
            request(model, "C:\\Users\\XCG\\Documents\\WeChat Files\\xcg_1103\\Files\\apiclient_cert.p12");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
