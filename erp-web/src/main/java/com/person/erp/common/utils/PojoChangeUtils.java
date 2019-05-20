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
     * @param entityList
     * @param dtoList
     * @param dtoClass
     */
    public static <T, E> void copyEntityList2DTOList(List<T> entityList, List<E> dtoList, Class<E> dtoClass) {

        if (entityList != null && dtoList != null) {

            entityList.forEach(entity -> {

                E dto = BeanUtils.instantiate(dtoClass);

                BeanUtils.copyProperties(entity, dto);

                dtoList.add(dto);

            });

        }

    }

}
