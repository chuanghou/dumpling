package com.stellariver.example.com.stellariver.dumpling;

import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DumplingLogger{

    private Logger log;

    private final ThreadLocal<Map<String, String>> logContents = new ThreadLocal<>();

    private final ThreadLocal<Map<String, String>> tempLogContents = new ThreadLocal<>();

    public DumplingLogger(Logger log){
        this.log = log;
        this.logContents.set(new HashMap<>());
        this.tempLogContents.set(new HashMap<>());
    }

    public DumplingLogger itemId(Object value) {
        Optional.ofNullable(value).ifPresent(v -> logContents.get().put("itemId", v.toString()));
        return this;
    }

    private void beforeLog() {
        Map<String, String> realLogContents = logContents.get();
        realLogContents.forEach((k, v) -> {
            String originalValue = MDC.get(k);
            if (v != null && originalValue != null) {
                tempLogContents.get().put(k, originalValue);
            }
            MDC.put(k, v);
        });
        realLogContents.clear();
    }

    public void log(String message, Throwable throwable) {
        beforeLog();
        log.info(message, throwable);
        afterLog();
    }

    private void afterLog() {
        tempLogContents.get().forEach(MDC::put);
        tempLogContents.get().clear();
    }

}
