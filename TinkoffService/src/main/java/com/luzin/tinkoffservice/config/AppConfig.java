package com.luzin.tinkoffservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Value("${sandBoxMode}")
    private boolean sandBoxMode;
    @Value("${token}")
    private String token;

    @Bean
    public InvestApi api(){
        return sandBoxMode ? InvestApi.createSandbox(token):InvestApi.create(token);
    }
}
