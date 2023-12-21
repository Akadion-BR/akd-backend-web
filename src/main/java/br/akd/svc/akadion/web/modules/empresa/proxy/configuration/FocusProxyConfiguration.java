package br.akd.svc.akadion.web.modules.empresa.proxy.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FocusProxyConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(System.getenv("TOKEN_FOCUS_PRODUCAO"), "");
    }

}
