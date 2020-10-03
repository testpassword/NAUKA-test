package com.kulbako.backend.config;

import org.slf4j.*;
import org.springframework.context.annotation.Bean;

/**
 * Класс, представляющий служебные бины на глобальном уровне.
 * @author Артемий Кульбако
 * @version 1.0
 */
public class ApplicationScopeBeansProvider {

    @Bean
    public Logger logger() { return LoggerFactory.getLogger("application"); }
}
