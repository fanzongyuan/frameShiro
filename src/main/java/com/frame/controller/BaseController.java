package com.frame.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseController
 *
 * @author FANZONGYUAN
 * @date 2015/12/4
 */
public class BaseController {
    private static final long serialVersionUID = 6718838822334455667L;
    //获得客户端真实IP地址
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
