package com.person.erp.common.utils;

import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.identity.entity.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Session工具类
 *
 * @author zhuwj
 * @description 操作 token 工具类
 * @since 2017/9/27 14:30
 */
public class TokenUtils {

    private final static String TOKEN_KEY = "283;%$09*&";

    private final static String TOKEN_NAME = "token";

    /**
     * 获取当前的 Request 域对象
     * @return HttpServletRequest
     * @author zhuwj
     */
    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 加密
     * @author zhuwj
     * @since 2019/5/9 11:35
     * @param str 要加密的字符串
     * @return java.lang.String
     */
    private static String encode(String str) {
        if (JudgeUtils.isEmpty(str)) {
            return str;
        }
        return MD5Utils.getMD5(str + TOKEN_KEY);
    }

    /**
     * 将 User 记录到 Redis 中
     * @author zhuwj
     * @since 2019/5/9 11:35
     * @param user
     * @param timeout
     * @param unit
     * @return java.lang.String
     */
    public static String recordUser(User user, long timeout, TimeUnit unit) {
        if (user == null) {
            return null;
        }
        String token = encode(User.class.getSimpleName() + user.getSystemTag() + user.getUserCode());
        RedisUtils.set(token, user, timeout, unit);
        return token;
    }

    /**
     * 从 Redis 中取出当前登录的用户
     * @param token token
     * @return User
     */
    public static User getUser(String token) {
        if (!JudgeUtils.isEmpty(token) && RedisUtils.exist(token)) {
            return (User) RedisUtils.get(token);
        }
        return null;
    }

    /**
     * 先从 Request 中取出 token, 再从 Redis 中取出当前登录的用户。
     * @return User
     */
    @Nullable
    public static User getUser() {
        String token = getHttpRequest().getParameter(TOKEN_NAME);
        if (!JudgeUtils.isEmpty(token) && RedisUtils.exist(token)) {
            return (User) RedisUtils.get(token);
        }
        return null;
    }

    /**
     * 判断是否是超级管理员
     * @return boolean
     */
    public static boolean superManager() {
        User user = getUser();
        return user != null && user.getSystemTag() == 0;
    }

}
