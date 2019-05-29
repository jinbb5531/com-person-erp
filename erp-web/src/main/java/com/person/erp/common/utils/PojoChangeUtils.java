package com.person.erp.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>PojoChangeUtils.java</p>
 *
 * @author zhuwj
 * @since 2019/5/19 9:34
 */
public class PojoChangeUtils {

    /**
     * 将 entityList 转为 dtoList
     * @author zhuwj
     * @since 2019/5/19 9:45
     * @param sourceList
     * @param targetList
     * @param targetClass
     */
    public static <T, E> void copyList(List<T> sourceList, List<E> targetList, Class<E> targetClass) {

        if (sourceList != null && targetList != null) {

            sourceList.forEach(source -> {

                E target = BeanUtils.instantiateClass(targetClass);

                BeanUtils.copyProperties(source, target);

                targetList.add(target);

            });

        }

    }

}
