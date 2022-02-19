package com.stellariver.example;

import com.stellariver.example.com.stellariver.dumpling.DumplingLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    private static final DumplingLogger logger;

    static {
        logger = new DumplingLogger(log);
    }

    public static void main(String[] args) {
        logger.with("itemId", 1L).info("test");
    }
}