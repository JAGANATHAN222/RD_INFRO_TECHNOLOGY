package com.RD_Technology.Internship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComputerStockManagerApplication {

    private static final Logger log = LoggerFactory.getLogger(ComputerStockManagerApplication.class);

    public static void main(String[] args) {
        log.info("Starting computer-stock-manager application");
        SpringApplication.run(ComputerStockManagerApplication.class, args);
    }
}

