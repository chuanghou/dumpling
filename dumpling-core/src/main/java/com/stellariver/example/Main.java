package com.stellariver.example;

import com.stellariver.dumpling.DumplingLogger;
import com.stellariver.dumpling.MortalMap;
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

    public static void main(String[] args) throws InterruptedException {
        MortalMap<String, String> map = new MortalMap<>();
        map.put("100", "100", 100);
        map.forEach((k, v) -> System.out.println(k));
        Thread.sleep(200);
        map.forEach((k, v) -> System.out.println(k));
        map.put("200", "200", 100);
        map.forEach((k, v) -> System.out.println(k));
    }
}