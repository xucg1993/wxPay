import com.xucg.model.WxPayRedEnvelopeModel;
import com.xucg.pay.WxPayRedEnvelope;

/**
 * 微信红包测试
 *
 * @author xucg
 * @since 2018/2/26
 */
public class WxPayRedEnvelopeModelTest {
    public static void main(String[] args) throws Exception {

        WxPayRedEnvelopeModel model = new WxPayRedEnvelopeModel("1491328582",//犁里服务商户号
                "1502029451",//水站子商户号
                "wxd79ceb5d2f034a68",//犁里公众号appid
                "wxc66d3d0c1e281982",//水站公众号appid
                "2oeTUVFrBRrHaW0I6Bz4On88d511OAT4");//犁里服务商支付密钥

        //订单号
        model.setMchBillno("20180514092741052000004");
        //商户名称
        model.setSendName("配送先生");
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
//        model.setSceneId("PRODUCT_5");
        //证书路径
        WxPayRedEnvelope.request(model, "C:\\Users\\lili\\Desktop\\证书\\apiclient_cert.p12");

    }
}
