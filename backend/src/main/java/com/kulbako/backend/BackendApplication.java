package com.kulbako.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа в приложение на базе Spring Framework.
 * @author Артемий Кульбако
 * @version 1.0
 */
@SpringBootApplication
public class BackendApplication {

    /**
     * Запускает приложение на выполнение.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}