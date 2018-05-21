package com.xucg.util.wx;

import com.xucg.config.WxPayConfigEnum;
import com.xucg.model.WxPayResultModel;
import com.xucg.util.string.StringUtil;

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

    public static boolean isPayReturnSuccess(WxPayResultModel result) {
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


}
