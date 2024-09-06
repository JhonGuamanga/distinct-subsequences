package com.appgate.dtp.shared.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Objects;

/**
 * Appgate logger to handle context information of distributed transactions using MDC
 *
 * @Author nmarmolejo
 */
public class AppgateLogger {

    private final Logger log;

    private AppgateLogger(String clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    public static AppgateLogger getLogger(String classname) {
        return new AppgateLogger(classname);
    }

    public void debug(String message) {
        log.debug(message);
    }

    public void debugCtx(String message, String contextId) {
        fillContextInfo(contextId);
        log.debug(message);
    }

    public void debug(String message, Object o) {
        log.debug(message, o);
    }

    public void debugCtx(String message, Object o, String contextId) {
        fillContextInfo(contextId);
        log.debug(message, o);
    }

    public void debug(String message, Object o, Object o1) {
        log.debug(message, o, o1);
    }

    public void debugCtx(String message, Object o, Object o1, String contextId) {
        fillContextInfo(contextId);
        log.debug(message, o, o1);
    }

    public void debug(String message, Object... objects) {
        log.debug(message, objects);
    }

    public void debugCtx(String message, String contextId, Objects... objects) {
        fillContextInfo(contextId);
        log.debug(message, objects);
    }

    public void debug(String message, Throwable throwable) {
        log.debug(message, throwable);
    }

    public void debugCtx(String message, Throwable throwable, String contextId) {
        fillContextInfo(contextId);
        log.debug(message, throwable);
    }

    public void info(String message) {
        log.info(message);
    }

    public void infoCtx(String message, String contextId) {
        fillContextInfo(contextId);
        log.info(message);
    }

    public void info(String message, Object o) {
        log.info(message, o);
    }

    public void infoCtx(String message, Object o, String contextId) {
        fillContextInfo(contextId);
        log.info(message, o);


    }

    public void info(String message, Object o, Object o1) {
        log.info(message, o, o1);
    }

    public void infoCtx(String message, Object o, Object o1, String contextId) {
        fillContextInfo(contextId);
        log.info(message, o, o1);
    }

    public void info(String message, Object... objects) {
        log.info(message, objects);
    }

    public void infoCtx(String message, String contextId, Objects... objects) {
        fillContextInfo(contextId);
        log.info(message, objects);
    }

    public void info(String s, Throwable throwable) {
        log.info(s, throwable);
    }

    public void infoCtx(String message, Throwable throwable, String contextId) {
        fillContextInfo(contextId);
        log.info(message, throwable);
    }

    public void errorCtx(String message, String contextId, Throwable throwable) {
        fillContextInfo(contextId);
        log.error(message, throwable);
    }

    public void error(String s) {
        log.error(s);
    }

    public void error(String s, Object o) {
        log.error(s, o);
    }

    public void error(String s, Object o, Object o1) {
        log.error(s, o, o1);
    }

    public void error(String s, Object... objects) {
        log.error(s, objects);
    }

    public void error(String s, Throwable throwable) {
        log.error(s, throwable);
    }

    private void fillContextInfo(String contextId) {
        MDC.put("context_id", contextId);
        if (MDC.get("transaction_context_id") == null)
            MDC.put("transaction_context_id", contextId);
    }

}
