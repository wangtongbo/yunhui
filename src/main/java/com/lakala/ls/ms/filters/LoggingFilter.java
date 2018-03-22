package com.lakala.ls.ms.filters;

import com.lakala.ls.ms.Current;
import com.lakala.ls.ms.domain.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.MDC;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

/**
 * Created by chenjian on 2017/5/22.
 */
public class LoggingFilter extends AbstractRequestLoggingFilter {

    public static final String DEFAULT_BEFORE_MESSAGE_PREFIX = "request [";

    public static final String DEFAULT_BEFORE_MESSAGE_SUFFIX = "]";

    public static final String DEFAULT_AFTER_MESSAGE_PREFIX = "response [";

    public static final String DEFAULT_AFTER_MESSAGE_SUFFIX = "]";

    private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 1024;

    private int maxPayloadLength = DEFAULT_MAX_PAYLOAD_LENGTH;

    private String beforeMessagePrefix = DEFAULT_BEFORE_MESSAGE_PREFIX;

    private String beforeMessageSuffix = DEFAULT_BEFORE_MESSAGE_SUFFIX;

    private String afterMessagePrefix = DEFAULT_AFTER_MESSAGE_PREFIX;

    private String afterMessageSuffix = DEFAULT_AFTER_MESSAGE_SUFFIX;

    private boolean includeQueryString = false;

    private boolean includeClientInfo = false;

    private boolean includeHeaders = false;

    private boolean includePayload = false;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("_start", now());
        MDC.put("requestId", UUID.randomUUID().toString());
        User user = Current.get();
        if(user!=null){
            MDC.put("userId", String.valueOf(user.getId()));
        }

        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        HttpServletResponse responseToUse = response;

        if (isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request);
        }
        if (isIncludePayload() && isFirstRequest && !(response instanceof ContentCachingResponseWrapper)) {
            responseToUse = new ContentCachingResponseWrapper(response);
        }

        if (isFirstRequest) {
            beforeRequest(requestToUse, getBeforeMessage(requestToUse));
        }
        try {
            filterChain.doFilter(requestToUse, responseToUse);
        }
        finally {
            if (!isAsyncStarted(requestToUse)) {
                afterResponse(responseToUse, getAfterMessage(requestToUse, responseToUse));
            }
        }
    }

    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }

    protected void afterResponse(HttpServletResponse response, String message) {
        logger.info(message);
    }

    /**
     * Get the message to write to the log before the request.
     * @see #createMessage
     */
    private String getBeforeMessage(HttpServletRequest request) {
        return createMessage(request, this.beforeMessagePrefix, this.beforeMessageSuffix);
    }

    /**
     * Get the message to write to the log before the request.
     * @see #createMessage
     */
    private String getAfterMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return createMessage(request, response, this.afterMessagePrefix, this.afterMessageSuffix);
    }

    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        StringBuilder msg = new StringBuilder();
        msg.append(prefix);
        msg.append("uri=").append(request.getRequestURI());
        msg.append(";method=").append(request.getMethod());

        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                msg.append('?').append(queryString);
            }
        }

        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";client=").append(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";session=").append(session.getId());
            }
            User user = Current.get();
            if (user != null) {
                msg.append(";user=").append(user.getId());
            }
        }

        if (isIncludeHeaders()) {
            msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());
        }

        if (isIncludePayload()) {
            ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }
                    msg.append(";payload=").append(payload);
                }
            }
        }

        msg.append(suffix);
        return msg.toString();
    }

    protected String createMessage(HttpServletRequest request, HttpServletResponse response, String prefix, String suffix)
        throws IOException {
        StringBuilder msg = new StringBuilder();
        msg.append(prefix);
        long start = (long) request.getAttribute("_start");
        msg.append("took=").append((now() - start) +"ms");
        msg.append(";status=").append(response.getStatus());
        if (isIncludePayload()) {
            ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {

                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }
                    msg.append(";payload=").append(payload);
                }
                wrapper.copyBodyToResponse();
            }
        }


        msg.append(suffix);
        return msg.toString();
    }

    private long now(){
        return System.currentTimeMillis();
    }
    public boolean isIncludePayload() {
        return includePayload;
    }

    public void setIncludePayload(boolean includePayload) {
        this.includePayload = includePayload;
    }

    public boolean isIncludeQueryString() {
        return includeQueryString;
    }

    public void setIncludeQueryString(boolean includeQueryString) {
        this.includeQueryString = includeQueryString;
    }

    public boolean isIncludeClientInfo() {
        return includeClientInfo;
    }

    public void setIncludeClientInfo(boolean includeClientInfo) {
        this.includeClientInfo = includeClientInfo;
    }

    public boolean isIncludeHeaders() {
        return includeHeaders;
    }

    public void setIncludeHeaders(boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
    }

    public int getMaxPayloadLength() {
        return maxPayloadLength;
    }

    public void setMaxPayloadLength(int maxPayloadLength) {
        this.maxPayloadLength = maxPayloadLength;
    }

    public String getBeforeMessagePrefix() {
        return beforeMessagePrefix;
    }

    public void setBeforeMessagePrefix(String beforeMessagePrefix) {
        this.beforeMessagePrefix = beforeMessagePrefix;
    }

    public String getBeforeMessageSuffix() {
        return beforeMessageSuffix;
    }

    public void setBeforeMessageSuffix(String beforeMessageSuffix) {
        this.beforeMessageSuffix = beforeMessageSuffix;
    }

    public String getAfterMessagePrefix() {
        return afterMessagePrefix;
    }

    public void setAfterMessagePrefix(String afterMessagePrefix) {
        this.afterMessagePrefix = afterMessagePrefix;
    }

    public String getAfterMessageSuffix() {
        return afterMessageSuffix;
    }

    public void setAfterMessageSuffix(String afterMessageSuffix) {
        this.afterMessageSuffix = afterMessageSuffix;
    }
}

