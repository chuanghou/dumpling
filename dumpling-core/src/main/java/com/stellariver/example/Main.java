package com.stellariver.example;

import com.stellariver.example.com.stellariver.dumpling.DumplingLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    private static final DumplingLogger dumplingLogger;

    static {
        dumplingLogger = new DumplingLogger(log);
    }

    public static void main(String[] args) {
        dumplingLogger.itemId(1L).log("test", new Exception());
    }
}