package com.universityapp.common.logger;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class LoggerUtil {

    private static String getRequestId() {
        HttpServletRequest req =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (String) req.getAttribute("request_id");
    }

    // Method to set MDC attributes from a map and log the message
    private static void setMDCAttributes(Map<String, Object> attributes) {
        // Add request_id if available
        String requestId = getRequestId();
        if (requestId != null) {
            attributes.putIfAbsent("request_id", requestId);
        }

        // Set each attribute in MDC
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            MDC.put(entry.getKey(), String.valueOf(entry.getValue())); // Convert object to string
        }
    }

    // Method to clear MDC context
    private static void clearMDC() {
        MDC.clear();
    }

    // Info level logging
    public static void info(
        Logger logger,
        String msg,
        Map<String, Object> attributes
    ) {
        setMDCAttributes(attributes);
        logger.info(msg);
        clearMDC();
    }

    // Warn level logging
    public static void warn(
        Logger logger,
        String msg,
        Map<String, Object> attributes
    ) {
        setMDCAttributes(attributes);
        logger.warn(msg);
        clearMDC();
    }

    // Error level logging
    public static void error(
        Logger logger,
        String msg,
        Map<String, Object> attributes
    ) {
        setMDCAttributes(attributes);
        logger.error(msg);
        clearMDC();
    }

    // Debug level logging
    public static void debug(
        Logger logger,
        String msg,
        Map<String, Object> attributes
    ) {
        setMDCAttributes(attributes);
        logger.debug(msg);
        clearMDC();
    }

    // Trace level logging
    public static void trace(
        Logger logger,
        String msg,
        Map<String, Object> attributes
    ) {
        setMDCAttributes(attributes);
        logger.trace(msg);
        clearMDC();
    }
}
