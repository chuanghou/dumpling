package com.stellariver.example.com.stellariver.dumpling;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DumplingLogger implements Logger{

    private final Logger log;

    private final ThreadLocal<Map<String, String>> logContents = new ThreadLocal<>();

    private final ThreadLocal<Map<String, String>> tempLogContents = new ThreadLocal<>();

    public DumplingLogger(Logger log){
        this.log = log;
        this.logContents.set(new HashMap<>());
        this.tempLogContents.set(new HashMap<>());
    }

    public DumplingLogger with(String key, Object value) {
        if (key == null) {
            log.error("log key shouldn't be null");
        }
        value = Optional.ofNullable(key).orElse("null");
        logContents.get().put(key, value.toString());
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

    @Override
    public String getName() {
        return log.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        beforeLog();
        log.trace(msg);
        afterLog();
    }

    @Override
    public void trace(String format, Object arg) {
        beforeLog();
        log.trace(format, arg);
        afterLog();
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        beforeLog();
        log.trace(format, arg1, arg2);
        afterLog();
    }

    @Override
    public void trace(String format, Object... arguments) {
        beforeLog();
        log.trace(format, arguments);
        afterLog();
    }

    @Override
    public void trace(String msg, Throwable t) {
        beforeLog();
        log.trace(msg, t);
        afterLog();
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return log.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        beforeLog();
        log.trace(marker, msg);
        afterLog();
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        beforeLog();
        log.trace(marker, format, arg);
        afterLog();
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        beforeLog();
        log.trace(marker, format, arg1, arg2);
        afterLog();
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        beforeLog();
        log.trace(marker, format, argArray);
        afterLog();
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        beforeLog();
        log.trace(marker, msg, t);
        afterLog();
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        beforeLog();
        log.debug(msg);
        afterLog();
    }

    @Override
    public void debug(String format, Object arg) {
        beforeLog();
        log.debug(format, arg);
        afterLog();
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        beforeLog();
        log.debug(format, arg1, arg2);
        afterLog();
    }

    @Override
    public void debug(String format, Object... arguments) {
        beforeLog();
        log.debug(format, arguments);
        afterLog();
    }

    @Override
    public void debug(String msg, Throwable t) {
        beforeLog();
        log.debug(msg, t);
        afterLog();
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return log.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        beforeLog();
        log.debug(marker, msg);
        afterLog();
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        beforeLog();
        log.debug(marker, format, arg);
        afterLog();
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        beforeLog();
        log.debug(marker, format, arg1, arg2);
        afterLog();
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        beforeLog();
        log.debug(marker, format, argArray);
        afterLog();
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        beforeLog();
        log.debug(marker, msg, t);
        afterLog();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        beforeLog();
        log.info(msg);
        afterLog();
    }

    @Override
    public void info(String format, Object arg) {
        beforeLog();
        log.info(format, arg);
        afterLog();
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        beforeLog();
        log.info(format, arg1, arg2);
        afterLog();
    }

    @Override
    public void info(String format, Object... arguments) {
        beforeLog();
        log.info(format, arguments);
        afterLog();
    }

    @Override
    public void info(String msg, Throwable t) {
        beforeLog();
        log.info(msg, t);
        afterLog();
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return log.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        beforeLog();
        log.trace(marker, msg);
        afterLog();
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        beforeLog();
        log.info(marker, format, arg);
        afterLog();
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        beforeLog();
        log.info(marker, format, arg1, arg2);
        afterLog();
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        beforeLog();
        log.info(marker, format, argArray);
        afterLog();
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        beforeLog();
        log.info(marker, msg, t);
        afterLog();
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String msg) {

    }

    @Override
    public void warn(String format, Object arg) {

    }

    @Override
    public void warn(String format, Object... arguments) {

    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {

    }

    @Override
    public void warn(String msg, Throwable t) {

    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String msg) {
        beforeLog();
        log.warn(marker, msg);
        afterLog();
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        beforeLog();
        log.warn(marker, format, arg);
        afterLog();
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        beforeLog();
        log.warn(marker, format, arg1, arg2);
        afterLog();
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        beforeLog();
        log.warn(marker, format, argArray);
        afterLog();
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        beforeLog();
        log.warn(marker, msg, t);
        afterLog();
    }
    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        beforeLog();
        log.error(msg);
        afterLog();
    }

    @Override
    public void error(String format, Object arg) {
        beforeLog();
        log.error(format, arg);
        afterLog();
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        beforeLog();
        log.error(format, arg1, arg2);
        afterLog();
    }

    @Override
    public void error(String format, Object... arguments) {
        beforeLog();
        log.error(format, arguments);
        afterLog();
    }

    @Override
    public void error(String msg, Throwable t) {
        beforeLog();
        log.error(msg, t);
        afterLog();
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return log.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        beforeLog();
        log.error(marker, msg);
        afterLog();
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        beforeLog();
        log.error(marker, format, arg);
        afterLog();
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        beforeLog();
        log.error(marker, format, arg1, arg2);
        afterLog();
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        beforeLog();
        log.error(marker, format, argArray);
        afterLog();
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        beforeLog();
        log.error(marker, msg, t);
        afterLog();
    }
}
