import com.xucg.config.DefaultWeiXinModel;
import com.xucg.model.WxPayPrePayModel;
import com.xucg.pay.WxPay;

/**
 * @author xuchenguang
 * @date 2018.05.21
 */
public class WxPayTest {

    public static void main(String[] args) {
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

        model.setOpenId("o6iPM4gzFb3ufH3KcIz0N9tLwXP0");

        WxPay.miniAppPay(model);
    }
}
