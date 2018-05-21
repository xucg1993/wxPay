package com.xucg.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xucg.model.WxPayTransferDibModel;
import com.xucg.util.wx.ClientCustomSSL;
import com.xucg.util.wx.WXPayUtil;
import com.xucg.util.wx.WxFormatParamUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchenguang
 * @date 2018.05.21
 */
public class GetPublicKey {

    private static final String URL = "https://fraud.mch.weixin.qq.com/risk/getpublickey";

    public static String request(String mchId, String payKey, String filePath) throws Exception {
        Map<String, Object> data = new HashMap<>(16);
        data.put("mch_id", mchId);
        data.put("nonce_str", WxFormatParamUtil.getNonceStr());
        data.put("sign_type", "MD5");

        //签名算法计算得出的签名值
        String sign = WXPayUtil.generateSignature(data, payKey);
        data.put("sign", sign);

        String payXml = WXPayUtil.mapToXml(data);

        String request = ClientCustomSSL.request(URL, mchId, payXml, filePath);

        return request;
    }

    public static void main(String[] args) throws Exception {
        String requestXml = request("1502029451", "WRYEC0EgrjGdbxU5AVZBRpYQ7r95Dmc9", "C:\\Users\\lili\\Desktop\\apiclient_cert.p12");
        Map<String, Object> map = WXPayUtil.xmlToMap(requestXml);
        String returnMsg = map.get("return_msg").toString();
        String resultCode = map.get("result_code").toString();
        String mchId = map.get("mch_id").toString();
        String pubKey = map.get("pub_key").toString();
        String returnCode = map.get("return_code").toString();
        System.out.println(pubKey);


    }
}
