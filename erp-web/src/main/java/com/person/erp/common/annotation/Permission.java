package com.person.erp.common.annotation;

import java.lang.annotation.*;

/**
 * <p>Permission.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 15:41
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String modelName() default "";

    /**
     * 类上使用时，若用户没有权限，也会将该值作为提示的一部分。如：【类上的value - 方法上的value】. 类上建议设置为模块名;
     * 方法上使用时，建议写操作名
     */
    String name() default "";

}
