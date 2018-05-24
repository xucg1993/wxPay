import com.xucg.model.WxPayMicroModel;
import com.xucg.model.WxPayTransferDibModel;
import com.xucg.pay.WxPayMicro;

/**
 * 转账测试
 *
 * @author xucg
 * @since 2018/2/26
 */
public class WxPayMicroTest {
    public static void main(String[] args) throws Exception {

        WxPayMicroModel model = new WxPayMicroModel("wxfef98f47a19e1173", "1500793841", "SnJBOF6jI4LvPlV0wfshpBGf8XWFDywb");
        model.setBody("测试");
        model.setOutTradeNo("201805241102");
        model.setTotalFee(1);
        model.setAuthCode("134626867439258267");
        model.setSpbillCreateIp("127.0.0.1");
        WxPayMicro.request(model);

    }
}
