package com.xucg.pay;

import com.xucg.config.DefaultWeiXinModel;
import com.xucg.model.WeiXinPrePay;
import com.xucg.model.WeiXinRedEnvelopeModel;

/**
 * 微信扫码支付
 * 二维码生成接口
 *
 * @author xucg
 * @since 2018/2/26
 */
public class test {
    public static void main(String[] args) {
//        WeiXinPrePay weiXinPrePay = DefaultWeiXinModel.getDefaultWeiXinModel();
//
//        //商品描述
//        weiXinPrePay.setBody("测试");
//        //订单编号
//        weiXinPrePay.setOutTradeNo("20180226123156");
//        //订单金额   单位：分
//        weiXinPrePay.setTotalFee(1000);
//        //终端Ip
//        weiXinPrePay.setSpbillCreateIp("127.0.0.1");
//        //回调地址
//        weiXinPrePay.setNotifyUrl("http://www.baidu.com");
//
//        weiXinPrePay.setOpenId("o6iPM4gzFb3ufH3KcIz0N9tLwXP0");
//
//        String scanCodePay = WxPay.miniAppPay(weiXinPrePay);


//        WeiXinPrePay prePay = new WeiXinPrePay("WRYEC0EgrjGdbxU5AVZBRpYQ7r95Dmc9");
//        prePay.setMchAppId("wxb24d7e2ad00d103b");
//        prePay.setqMchId("1502029451");
//        prePay.setOpenId("o6iPM4rL6rkaK4rHHh7uxRdxa9wM");
//        prePay.setPartnerTradeNo("20181123100312");
//        prePay.setCheckName("FORCE_CHECK");
//        prePay.setReUserName("徐晨光");
//        prePay.setAmount(100);
//        prePay.setDesc("测试");
//        prePay.setSpbillCreateIp("127.0.0.1");
//        String s = "";
//        try {
//            s = QYWxPay.transfers(prePay, "C:\\Users\\lili\\Desktop\\apiclient_cert.p12");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        WeiXinRedEnvelopeModel model = new WeiXinRedEnvelopeModel("1491328582",//犁里服务商户号
                "1502029451",//水站子商户号
                "wxd79ceb5d2f034a68",//犁里公众号appid
                "wxc66d3d0c1e281982",//水站公众号appid
                "2oeTUVFrBRrHaW0I6Bz4On88d511OAT4");//犁里服务商支付密钥

        //订单号
        model.setMchBillno("20180514092741052000001");
        //商户名称
        model.setSendName("测试");
        //用户openid   水站公众号的openid
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
        //场景id    红包金额大于200元时必传
        //model.setSceneId("PRODUCT_5");
        try {
            //证书路径
            WxPayRedEnvelope.request(model, "C:\\Users\\XCG\\Documents\\WeChat Files\\xcg_1103\\Files\\apiclient_cert.p12");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
