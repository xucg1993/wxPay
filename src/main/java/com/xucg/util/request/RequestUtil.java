package com.xucg.util.request;


import com.xucg.util.pattern.PatternUtil;
import com.xucg.util.string.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RequestUtil {
    public final static String POST = "POST";
    public final static String GET = "GET";

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String content;
            while ((content = reader.readLine()) != null) {
                sb.append(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean isPostRequest(HttpServletRequest request) {
        return POST.equalsIgnoreCase(request.getMethod());
    }

    public static boolean isGetRequest(HttpServletRequest request) {
        return GET.equalsIgnoreCase(request.getMethod());
    }

    public static String getUserAgent(HttpServletRequest request) {
        return getHeader(request, "user-agent");
    }

    public static String getHeader(HttpServletRequest request, String header) {
        return request.getHeader(header);
    }

    public static String getRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!StringUtil.isNullorEmpty(ip)) {
            return ip;
        }

        if (StringUtil.isNullorEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isNullorEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isNullorEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (StringUtil.isNullorEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getDomain(HttpServletRequest request) {
        String serverName = request.getServerName();
        return serverName.contains(".com") ? PatternUtil.group(serverName, "[\\w]*\\.com") : null;
    }
}
