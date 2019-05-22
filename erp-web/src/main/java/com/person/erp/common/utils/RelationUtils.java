package com.person.erp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>RelationUtils.java</p>
 *
 * @author zhuwj
 * @since 2019/5/22 11:27
 */
public class RelationUtils {

    /**
     * 搜索出codes的所有子主键
     * @author zhuwj
     * @since 2019/5/22 11:28
     * @param codes
     * @param allParentMap
     * @return java.util.List<T>
     */
    public static <T> List<T> searchHaveChildCode(List<T> codes, Map<T,List<T>> allParentMap) {

        List<T> list = new ArrayList<>();

        if (codes != null && codes.size() > 0) {

            list.addAll(codes);

            for (T code : codes) {

                List<T> childCodes = allParentMap.get(code);

                // 不为空，有子，再用子codes去查他们各自的子（递归）放入codes中，；为空，无子，不处理
                if (childCodes != null) {

                    list.addAll(searchHaveChildCode(childCodes, allParentMap));

                }

            }

        }

        return list;
    }

}
