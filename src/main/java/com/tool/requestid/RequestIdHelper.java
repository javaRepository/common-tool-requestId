package com.tool.requestid;

import org.slf4j.MDC;

/**
 * Created by liang on 18-4-16.
 */
public class RequestIdHelper {

    public static final String HEADER_REQUEST_ID = "X-Head-RequestId";

    public static void setRequestId(String logId) {
        MDC.put(HEADER_REQUEST_ID, logId);
    }

    public static String getRequestId() {
        return MDC.get(HEADER_REQUEST_ID);
    }

    public static void removeRequestId() {
        MDC.remove(HEADER_REQUEST_ID);
    }

}
