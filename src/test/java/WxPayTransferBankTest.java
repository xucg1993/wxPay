import com.xucg.model.WxPayTransferBankModel;
import com.xucg.pay.WxPayTransferBank;

/**
 * 转账测试
 *
 * @author xucg
 * @since 2018/2/26
 */
public class WxPayTransferBankTest {
    public static void main(String[] args) throws Exception {
        WxPayTransferBankModel model = new WxPayTransferBankModel("1502029451", "WRYEC0EgrjGdbxU5AVZBRpYQ7r95Dmc9");
        model.setEncBankNo("6216910106201038");
        model.setEncTrueName("徐晨光");
        model.setBankCode("1006");
        model.setPartnerTradeNo("20181123100312");
        model.setAmount(100);
        WxPayTransferBank.request(model, "C:\\Users\\lili\\Desktop\\apiclient_cert.p12", "C:\\Users\\lili\\Desktop\\证书\\publicKey_PSCK#8.pem");
    }
}
