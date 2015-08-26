package cn.st.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * desciption: cookies 操作类
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月20日
 */
public class CookieUtil {

    private CookieUtil() {

    }

    /**
     * 添加cookie
     * 
     * @param response
     * @param name cookie名称
     * @param value cookie值
     * @param path cookie路径
     * @param maxAge 单位：天
     */
    public static void addCookie(HttpServletResponse response, String name, String value,
            String domain, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 读取cookie
     * 
     * @param request
     * @param name
     * @return
     */
    public static String readCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (StringUtils.isBlank(name) || ArrayUtils.isEmpty(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 清除cookie
     * 
     * @param response
     * @param name
     * @param domain
     * @param path
     */
    public static void removeCookie(HttpServletResponse response, String name, String domain,
            String path) {
        Cookie cookie = new Cookie(name, null);
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        if (!StringUtils.isBlank(path)) {
            cookie.setPath(path);
        }
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }
}
