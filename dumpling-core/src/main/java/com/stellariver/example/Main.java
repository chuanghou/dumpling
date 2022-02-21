package com.stellariver.example;

import com.stellariver.dumpling.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        log.with("itemId", 1L)
                .with("userId", 2222L)
                .error("第三方业务异常", new Exception());
    }
}