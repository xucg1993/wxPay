package com.xucg.config;

/**
 * @author xucg
 * @since 2017/11/8
 */
public enum WxPayConfigEnum {

    UNIFIEDORDER_URL("统一下单URL", "https://api.mch.weixin.qq.com/pay/unifiedorder"),

    MICRO_PAY_URL("统一下单URL", "https://api.mch.weixin.qq.com/pay/micropay"),

    ORDERQUERY_URL("查询订单URL", "https://api.mch.weixin.qq.com/pay/orderquery"),

    REFUND_URL("申请退款URL", "https://api.mch.weixin.qq.com/secapi/pay/refund"),

    REFUNDQUERY_URL("查询退款URL", "https://api.mch.weixin.qq.com/pay/refundquery"),

    CLOSEORDER_URL("关闭订单URL", "https://api.mch.weixin.qq.com/pay/closeorder"),

    WXPAY_NATIVE("原生扫码支付", "NATIVE"),

    WXPAY_JSAPI("小程序支付", "JSAPI"),

    WXPAY_MWEB("H5支付", "MWEB"),

    WXPAY_APP("APP支付", "APP"),


    SUCCESS("回调状态", "SUCCESS"),
    CHANGE("退款异常", "CHANGE"),
    REFUNDCLOSE("退款关闭", "REFUNDCLOSE"),

    TOTAL_FEE("订单金额", "total_fee"),

    RETURN_CODE("返回状态码", "return_code"),

    RESULT_CODE("返回信息", "result_code"),

    REFUND_CODE("退款返回信息", "refund_status"),

    ERR_CODE("错误代码", "err_code"),
    TRADE_STATE("错误代码", "trade_state");

    private String name;
    private String value;

    WxPayConfigEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
