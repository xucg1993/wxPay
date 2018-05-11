package com.xucg.pay;

import com.xucg.config.DefaultWeiXinModel;
import com.xucg.model.WeiXinPrePay;

/**
 * 微信扫码支付
 * 二维码生成接口
 *
 * @author xucg
 * @since 2018/2/26
 */
public class test {
    public static void main(String[] args) {
        WeiXinPrePay weiXinPrePay = DefaultWeiXinModel.getDefaultWeiXinModel();

        //商品描述
        weiXinPrePay.setBody("测试");
        //订单编号
        weiXinPrePay.setOutTradeNo("20180226123156");
        //订单金额   单位：分
        weiXinPrePay.setTotalFee(1000);
        //终端Ip
        weiXinPrePay.setSpbillCreateIp("127.0.0.1");
        //回调地址
        weiXinPrePay.setNotifyUrl("http://www.baidu.com");

        weiXinPrePay.setOpenId("o6iPM4gzFb3ufH3KcIz0N9tLwXP0");

        String scanCodePay = WxPay.miniAppPay(weiXinPrePay);
        System.out.println(scanCodePay);
    }
}
