package com.person.erp.common.utils;

import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.MenuDTO;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Token 工具类
 *
 * @author zhuwj
 * @description 操作 token 工具类
 * @since 2017/9/27 14:30
 */
public class TokenUtils {

    private final static String TOKEN_KEY = "283;%$09*&";

    private final static String TOKEN_HEADER_NAME = "X-Token";

    private final static String PERMISSION_KEY = "permission";

    private final static String MENU_KEY = "MENU";



    /**
     * 获取当前的 Request 域对象
     *
     * @return HttpServletRequest
     * @author zhuwj
     */
    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取请求中的token
     * @author zhuwj
     * @since 2019/5/31 22:37
     * @return java.lang.String
     */
    public static String getRequestToken() {
        return getHttpRequest().getHeader(TOKEN_HEADER_NAME);
    }

    /**
     * 加密
     *
     * @param str 要加密的字符串
     * @return java.lang.String
     * @author zhuwj
     * @since 2019/5/9 11:35
     */
    private static String encode(String str) {
        if (JudgeUtils.isEmpty(str)) {
            return str;
        }
        return MD5Utils.getMD5(str + TOKEN_KEY);
    }

    /**
     * 将 User 记录到 Redis 中
     *
     * @param user
     * @param timeout
     * @param unit
     * @return java.lang.String
     * @author zhuwj
     * @since 2019/5/9 11:35
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
     * 将用户从 Redis 中移除
     *
     * @param user
     * @author zhuwj
     * @since 2019/5/16 16:46
     */
    public static void removeUser(User user) {
        if (user != null) {
            String token = encode(User.class.getSimpleName() + user.getSystemTag() + user.getUserCode());
            RedisUtils.remove(token);
        }
    }

    /**
     * 从 Redis 中取出当前登录的用户
     *
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
     *
     * @return User
     */
    @Nullable
    public static User getUser() {
        String token = getRequestToken();
        if (!JudgeUtils.isEmpty(token) && RedisUtils.exist(token)) {
            return (User) RedisUtils.get(token);
        }
        return null;
    }

    /**
     * 判断是否是超级管理员
     *
     * @return boolean
     */
    public static boolean superManager() {
        User user = getUser();
        return user != null && user.getSystemTag() == 0;
    }

    /**
     * 记录权限
     *
     * @param token
     * @param menuList
     * @param timeout
     * @param unit
     * @author zhuwj
     * @since 2019/5/24 22:06
     */
    public static void recordPermission(String token, List<MenuDTO> menuList, long timeout, TimeUnit unit) {
        RedisUtils.set(token + PERMISSION_KEY, menuList, timeout, unit);
    }

    /**
     * 获取当前用户权限
     *
     * @return java.util.List<com.person.erp.identity.model.MenuDTO>
     * @author zhuwj
     * @since 2019/5/24 23:10
     */
    public static List<MenuDTO> getPermission() {
        String token = getRequestToken();
        return (List<MenuDTO>) RedisUtils.get(token + PERMISSION_KEY);
    }

    /**
     * 获取用户权限
     *
     * @param token
     * @return java.util.List<com.person.erp.identity.model.MenuDTO>
     * @author zhuwj
     * @since 2019/5/24 23:10
     */
    public static List<MenuDTO> getPermission(String token) {
        return (List<MenuDTO>) RedisUtils.get(token + PERMISSION_KEY);
    }

    /**
     * 清除当前用户的权限
     *
     * @param
     * @return
     * @author zhuwj
     * @since 2019/5/25 10:00
     */
    public static void removePermission() {
        String token = getRequestToken();
        if (!JudgeUtils.isEmpty(token)) {
            RedisUtils.remove(token + PERMISSION_KEY);
        }
    }

    /**
     * 刷新权限时间
     * @author zhuwj
     * @since 2019/5/31 16:56
     * @param menuList
     * @param timeout
     * @param unit
     * @return void
     */
    public static void refreshPermissionDate(List<MenuDTO> menuList, long timeout, TimeUnit unit) {
        String token = getRequestToken();
        recordPermission(token, menuList, timeout, unit);
    }

    /**
     * 存菜单集
     * @author zhuwj
     * @since 2019/6/26 0:50
     * @param token
     * @param menuList
     * @param timeout
     * @param minutes
     * @return void
     */
    public static void recordMenuList(String token, List<MenuDTO> menuList, long timeout, TimeUnit minutes) {
        RedisUtils.set(token + MENU_KEY, menuList, timeout, minutes);
    }

    /**
     * 刷新权限时间
     * @author zhuwj
     * @since 2019/6/26 0:51
     * @param menuList
     * @param timeout
     * @param unit
     * @return void
     */
    public static void refreshMenuListDate(List<MenuDTO> menuList, long timeout, TimeUnit unit) {
        String token = getRequestToken();
        recordMenuList(token, menuList, timeout, unit);
    }

    /**
     * 获取当前用户角色的所有菜单集
     * @author zhuwj
     * @since 2019/6/26 0:53
     * @return java.util.List<com.person.erp.identity.model.MenuDTO>
     */
    public static List<MenuDTO> getMenuList() {
        String token = getRequestToken();
        return (List<MenuDTO>) RedisUtils.get(token + MENU_KEY);
    }

    /**
     * 去除菜单List
     * @author zhuwj
     * @since 2019/6/26 1:02
     * @param
     * @return void
     */
    public static void removeMenuList() {
        String token = getRequestToken();
        if (!JudgeUtils.isEmpty(token)) {
            RedisUtils.remove(token + MENU_KEY);
        }
    }

    /**
     * 刷新权限树时间
     * @author zhuwj
     * @since 2019/6/26 1:02
     * @param timeout
     * @param minutes
     * @return void
     */
    public static void refreshPermissionDate(int timeout, TimeUnit minutes) {
        String token = getRequestToken();
        RedisUtils.expire(token + PERMISSION_KEY, timeout, minutes);
    }
}
