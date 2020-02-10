package com.xucg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuchenguang
 * @since 2019.01.02
 */
@Configuration
@ConditionalOnClass(WxPayConfigService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayAutoConfiguration {

    @Autowired
    private WxPayProperties wxPayProperties;

    @Bean
    @ConditionalOnMissingBean(WxPayConfigService.class)
    @ConditionalOnProperty(prefix = "wxpay", value = "enabled", havingValue = "true")
    WxPayConfigService wxPayConfigService() {
        return new WxPayConfigService(wxPayProperties);
    }
}