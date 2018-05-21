import com.xucg.model.WxPayTransferDibModel;
import com.xucg.pay.WxPayTransferDib;

/**
 * 转账测试
 * @author xucg
 * @since 2018/2/26
 */
public class WxPayTransferDibTest {
    public static void main(String[] args) throws Exception {

        WxPayTransferDibModel model = new WxPayTransferDibModel("wxb24d7e2ad00d103b", "1502029451", "WRYEC0EgrjGdbxU5AVZBRpYQ7r95Dmc9");
        model.setOpenId("o6iPM4rL6rkaK4rHHh7uxRdxa9wM");
        model.setPartnerTradeNo("20181123100312");
        model.setCheckName("FORCE_CHECK");
        model.setReUserName("徐晨光");
        model.setAmount(100);
        model.setDesc("测试");
        model.setSpbillCreateIp("127.0.0.1");
        WxPayTransferDib.request(model, "C:\\Users\\lili\\Desktop\\apiclient_cert.p12");

    }
}
