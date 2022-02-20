package com.stellariver.example;

import com.stellariver.dumpling.DumplingLogger;
import org.slf4j.Logger;

public class Main {

    private static final DumplingLogger log;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
    static {
        log = new DumplingLogger(logger);
    }

    static {
        System.out.println("test");
    }

    public static void main(String[] args) {
        log.with("itemId", 1L)
                .with("test", 1)
                .with("im", 34)
                .info("test");
    }
}