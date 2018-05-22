package com.xucg.pay;

import com.xucg.model.ResultJson;
import com.xucg.model.WxPayTransferBankModel;
import com.xucg.util.rsa.RSAUtils;
import com.xucg.util.wx.ClientCustomSSL;
import com.xucg.util.wx.WxFormatParamUtil;
import com.xucg.util.xml.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

/**
 * @author xuchenguang
 * @date 2018.05.07
 */
public class WxPayTransferBank {
    private static final Logger logger = LoggerFactory.getLogger("wxPay sdk");

    private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";
    private static final String RSA = "RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";
    private static final Integer KEY_LENGTH = 2048;
    private static final Integer RESERVE_SIZE = 11;

    /**
     * 企业商户向用户微信账户零钱转账
     *
     * @param model
     * @param filePath
     * @param pemFilePath
     * @return
     * @throws Exception
     */
    public static String request(WxPayTransferBankModel model, String filePath, String pemFilePath) throws Exception {

        try {
            PublicKey publicKey = RSAUtils.getPublicKey(pemFilePath);

            byte[] encBankNo = RSAUtils.encrypt(model.getEncBankNo().getBytes(), publicKey, KEY_LENGTH, RESERVE_SIZE, RSA);
            byte[] encTrueName = RSAUtils.encrypt(model.getEncTrueName().getBytes(), publicKey, KEY_LENGTH, RESERVE_SIZE, RSA);

            //Base64编码
            Base64.Encoder encoder = Base64.getEncoder();

            //收款方银行卡号
            model.setEncBankNo(encoder.encodeToString(encBankNo));
            //收款方用户名
            model.setEncTrueName(encoder.encodeToString(encTrueName));

            //签名算法计算得出的签名值
            model.setSign(WxPayTransferBankModel.buildSignStr(model));

            //申请退款XML
            String payXml = WxPayTransferBankModel.buildPayXml(model);

            String result = ClientCustomSSL.request(TRANSFERS_URL, model.getMchId(), payXml, filePath);

            Map<String, String> xmlValue = XmlUtil.getXmlValue(result);
            logger.info("微信转账到银行卡结果 : " + xmlValue);
            //判断结果
            if (WxFormatParamUtil.isPayReturnSuccess(xmlValue)) {
                return ResultJson.getResultJsonSuccess(xmlValue);
            }
            return ResultJson.getResultJsonFail(xmlValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信转账到银行卡结果 : 系统异常");
        return ResultJson.getResultJsonError();
    }
}
