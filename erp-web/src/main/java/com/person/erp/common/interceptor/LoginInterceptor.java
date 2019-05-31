package com.person.erp.common.interceptor;

import com.itexplore.core.api.model.ApiException;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>LoginInterceptor.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 17:42
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${token.timeout:30}")
    private int timeout;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        User user = TokenUtils.getUser();

        boolean flag = false;

        if (user != null) {
            // 更新缓存时间
            TokenUtils.recordUser(user, timeout, TimeUnit.MINUTES);

            flag = true;
        } else {
            // 未登录的；判断其cookie是否有存用户名，存了用户名，帮其做登录
//            Cookie cookie = CookieUtils.getCookie(WebConstant.COOKIE_NAME);
//            if (cookie != null) {
//                // 判断cookie是否被篡改过
//                String userCode = CookieUtils.getCookieValue(WebConstant.COOKIE_NAME);
//                String key = userCode + MD5CodeUtil.md5(userCode + CookieUtils.SAFE_KEY);
//                if (key.equals(cookie.getValue())) {
//                    // 未被改过，用户名是正确的
//                    try {
//                        flag = userService.autoLogin(userCode, 1);
//                    } catch (ServiceException e) {
//                        throw new ServiceException(ApiStatus.UNAUTHORIZED, e.getMessage());
//                    }
//                } else {
//                    CookieUtils.removeCookie(WebConstant.COOKIE_NAME);
//                    throw new ServiceException(ApiStatus.UNAUTHORIZED, "Cookie被篡改！");
//                }
//            }
        }

        if (!flag) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "请先登录用户！");
        } else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
