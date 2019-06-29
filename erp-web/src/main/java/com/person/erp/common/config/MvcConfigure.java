package com.person.erp.common.config;

import com.person.erp.common.interceptor.LoginInterceptor;
import com.person.erp.common.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <p>MvcConfigure.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 17:24
 */
@Configuration
public class MvcConfigure extends WebMvcConfigurationSupport {

    @Value("${permission.use:true}")
    private boolean permissionUse;

    @Value("${login.use:true}")
    private boolean loginUse;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        if (loginUse)
            registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**/*").excludePathPatterns(
                    "/api/file/**",
                    "/api/user/update/password/new",
                    "/api/sys/get/**",
                    "/api/login/**"
            );

        // 权限拦截
        if (permissionUse)
            registry.addInterceptor(permissionInterceptor).addPathPatterns("/api/**/*");

    }
}
