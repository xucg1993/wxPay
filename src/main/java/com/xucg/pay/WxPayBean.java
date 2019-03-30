package com.xucg.pay;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuchenguang
 * @since 2019.01.03
 */
@Configuration
@ConditionalOnClass(WxPay.class)
public class WxPayBean {

    @Bean
    @ConditionalOnMissingBean(WxPay.class)
    public WxPay wxPay() {
        return new WxPay();
    }

    @Bean
    @ConditionalOnMissingBean(WxPayMicro.class)
    public WxPayMicro wxPayMicro() {
        return new WxPayMicro();
    }

    @Bean
    @ConditionalOnMissingBean(WxPayTransferDib.class)
    public WxPayTransferDib wxPayTransferDib() {
        return new WxPayTransferDib();
    }
}
