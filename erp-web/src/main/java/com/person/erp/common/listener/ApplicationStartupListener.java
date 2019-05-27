package com.person.erp.common.listener;

import com.person.erp.common.annotation.Permission;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.identity.constant.IdentityConstant;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>ApplicationStartupListener.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 15:50
 */
@Component
@Slf4j
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${server.context-path:/}")
    private String contextPath;

    @Resource
    private IMenuService menuService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("启动监听器：ApplicationStartupListener ");
        // 防止使用外部servlet容器时启动时，执行两次
        if (event.getApplicationContext().getParent() == null) {

            ApplicationContext applicationContext = event.getApplicationContext();
            RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
            Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = mapping.getHandlerMethods();

            // 所有自定义注解的菜单集
            List<MenuDTO> menuList = new ArrayList<>();

            MenuDTO menu = null;

            contextPath = "/".equals(contextPath) ? "" : contextPath;

            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {

                HandlerMethod handlerMethod = entry.getValue();

                Permission annotation = handlerMethod.getMethod().getAnnotation(Permission.class);

                if (annotation != null) {

                    // 取该方法的url
                    Set<String> urlSet = entry.getKey().getPatternsCondition().getPatterns();

                    for (String url : urlSet) {

                        menu = new MenuDTO();
                        menu.setMenuName(annotation.name());
                        menu.setMenuUrl(contextPath + url);
                        menu.setModuleFlag(IdentityConstant.ModuleFlag.OPERATE.getValue());
                        menu.setUseFlag(IdentityConstant.UseFlag.USE.getValue());
                        menu.setShowFlag(WebConstant.ShowFlag.SHOW.getValue());
                        menuList.add(menu);

                    }

                }

            }

            menuService.insertMenuBatch(menuList);

        }
    }
}
