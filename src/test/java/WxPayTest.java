/*
import com.xucg.config.DefaultWeiXinModel;
import com.xucg.model.WxPayPrePayModel;
import com.xucg.pay.WxPay;

*/
/**
 * @author xuchenguang
 * @date 2018.05.21
 *//*

public class WxPayTest {

    public static void main(String[] args) throws Exception{
        WxPayPrePayModel model = DefaultWeiXinModel.getDefaultWeiXinModel();

        //商品描述
        model.setBody("测试");
        //订单编号
        model.setOutTradeNo("20180226123156");
        //订单金额   单位：分
        model.setTotalFee(1000);
        //终端Ip
        model.setSpbillCreateIp("127.0.0.1");
        //回调地址
        model.setNotifyUrl("http://www.baidu.com");

        model.setOpenId("oA4SZ5SUFqxep3u8N6WM1q1lPRnA");

        WxPay.miniAppPay(model);

//        WxPayPrePayModel model = DefaultWeiXinModel.getDefaultWeiXinModel();
//        //订单编号
//        model.setOutTradeNo("20180524194005033000001");
//
//        //退款单号
//        model.setOutRefundNo("20180524194005033000001");
//
//        //订单金额   单位：分
//        model.setTotalFee(3700);
//
//        //退款金额   单位：分
//        model.setRefundFee(3700);
//
//        WxPay.refund(model,"classpath:\\cert\\apiclient_cert.p12");
    }
}
*/
