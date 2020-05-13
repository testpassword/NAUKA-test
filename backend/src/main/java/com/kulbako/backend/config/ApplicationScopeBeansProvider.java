package com.kulbako.backend.config;

import org.slf4j.*;
import org.springframework.context.annotation.Bean;

public class ApplicationScopeBeansProvider {

    //TODO: установить здесь final если возможно иначе private и get()
    @Bean
    public Logger logger() { return LoggerFactory.getLogger("application"); }
}
