package com.notes.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 文件描述 获取客户端和本级IP地址：
 *      一般用HttpServletRequest对象的getRemoteAddr()方法即可获取。
 *      存在代理的情况下，请求头会加上一些特定信息，如:x-forwarded-for、Proxy-Client-IP、WL-Proxy-Client-IP等来指向ip地址
 **/
public class IPGetUtils {

    private static final String IP_UNKNOWN = "unknown";

    public static void main(String[] args) {
        String localIPAddr = getLocalIPAddr();
        System.out.println(localIPAddr);
    }

    /**
     * 获取客户端ip
     *      单个代理
     * @param request
     * @return
     */
    public static String getIPAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || StringUtils.equalsIgnoreCase(IP_UNKNOWN, ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || StringUtils.equalsIgnoreCase(IP_UNKNOWN, ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || StringUtils.equalsIgnoreCase(IP_UNKNOWN, ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取客户端ip
     *      存在多级代理时，则取第一个非 unknown 的IP
     * @param request
     * @return
     */
    public static String getIpAddrAdvanced(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 存在多级代理时
        if (ip != null && ip.indexOf(",") != -1) {
            String[] ipWithMultiProxy = ip.split(",");

            for(int i = 0; i < ipWithMultiProxy.length; ++i) {
                String eachIpSegment = ipWithMultiProxy[i];
                if (!StringUtils.equalsIgnoreCase(eachIpSegment, IP_UNKNOWN)) {
                    ip = eachIpSegment;
                    break;
                }
            }
        }

        return ip;
    }

    public static String getLocalIPAddr() {
        String ip = "";
        try {
            // 候选地址
            InetAddress candidateAddr = null;
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddresses.nextElement();
                    // 回环地址127.0.0.1
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr.getHostAddress();
                        } else {
                            candidateAddr = inetAddr;
                        }
                    }
                }
            }

            if (null != candidateAddr) {
                return candidateAddr.getHostAddress();
            }

            // 都没有使用jdk提供的地址
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ip;
    }
}