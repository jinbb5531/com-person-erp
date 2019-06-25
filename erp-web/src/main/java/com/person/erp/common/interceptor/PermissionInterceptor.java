package com.person.erp.common.interceptor;

import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.model.MenuDTO;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>PermissionInterceptor.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 17:10
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Value("${token.timeout:30}")
    private int timeout;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            // 请求servlet路径
            String servletPath = request.getServletPath();
            HandlerMethod handlerMethod = ((HandlerMethod) handler);
            StringBuilder desc = new StringBuilder();

            // 获取方法上的权限注解
            Permission methodPermission = handlerMethod.getMethod().getAnnotation(Permission.class);
            String moduleDesc = methodPermission == null ? "" : methodPermission.modelName();
            String methodDesc = methodPermission == null ? servletPath : methodPermission.name();

            // 方法上有使用@Permission才需要拦截
            if (methodPermission != null) {
                // 判断当前用户是否存在该权限
                boolean exist = existPermission(servletPath);
                if (!exist) {
                    boolean moduleExist = !JudgeUtils.isEmpty(moduleDesc);
                    desc.append("您没有【")
                            .append(moduleExist ? moduleDesc + " - " : "")
                            .append(methodDesc)
                            .append("】权限！");
                    // 不存在，要抛异常
                    throw new ApiException(HttpStatus.FORBIDDEN, desc.toString());
                }
            } else {
                return true;
            }
        }
        return true;

    }

    /**
     * 判断当前用户是否存在该权限
     * @param servletPath 方法访问路径
     * @return boolean
     * @author zhuwj
     */
    private boolean existPermission(String servletPath) {

        List<MenuDTO> menuList = TokenUtils.getMenuList();

        if (!JudgeUtils.isEmpty(menuList) && !JudgeUtils.isEmpty(servletPath)) {

            // 刷新权限缓存时间
            TokenUtils.refreshMenuListDate(menuList, timeout, TimeUnit.MINUTES);

            // 刷新权限缓存时间
            TokenUtils.refreshPermissionDate(timeout, TimeUnit.MINUTES);

            for (MenuDTO menu : menuList) {

                if (!JudgeUtils.isEmpty(menu.getMenuUrl()) && menu.getMenuUrl().trim().contains(servletPath)) {
                    return true;
                }

            }

        }

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
