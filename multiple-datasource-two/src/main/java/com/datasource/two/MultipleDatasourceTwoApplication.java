package com.datasource.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDatasourceTwoApplication {

    private static final Logger logger = LoggerFactory.getLogger(MultipleDatasourceTwoApplication.class);

    public static void main(String[] args) {

        logger.debug("MultipleDatasourceTwoApplication is begin ...");
        SpringApplication.run(MultipleDatasourceTwoApplication.class, args);
    }

}
