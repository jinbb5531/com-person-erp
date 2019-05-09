package com.person.erp.common.utils;

import com.person.erp.common.constant.WebConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Session工具类
 *
 * @author zhuwj
 * @description 操作Session工具类
 * @since 2017/9/27 14:30
 */
public class SessionUtils {

    /**
     * 获取当前的session域对象
     * @param
     * @return javax.servlet.http.HttpSession
     * @author zhuwj
     */
    public static HttpSession getHttpSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 往session域存放数据
     * @param name
     * @param value
     * @return void
     * @author zhuwj
     */
    public static void setAttributes(String name, Object value) {
        getHttpSession().setAttribute(name, value);
    }

    /**
     * 从session域取出数据
     * @param name
     * @return java.lang.Object
     * @author zhuwj
     */
    public static Object getAttributes(String name) {
        return getHttpSession().getAttribute(name);
    }

    /**
     * 将属性移出session域
     * @param name
     * @author zhuwj
     */
    public static void removeAttributes(String name) {
        getHttpSession().removeAttribute(name);
    }

    /**
     * 设置校验码
     * @param code
     * @author zhuwj
     */
    public static void setVerifyCode(String code) {
        setAttributes(WebConstant.VERIFY_CODE, code);
    }

    /**
     * 取出校验码
     * @return java.lang.String
     * @author zhuwj
     */
    public static String getVerifyCode() {
        return (String) getAttributes(WebConstant.VERIFY_CODE);
    }

}
