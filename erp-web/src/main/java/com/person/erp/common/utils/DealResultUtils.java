package com.person.erp.common.utils;

import com.itexplore.core.api.utils.ResultUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>DealResultUtils.java</p>
 *
 * @author zhuwj
 * @since 2019/5/21 9:47
 */
public class DealResultUtils {

    /**
     * 封装 单个数据 返回
     * @author zhuwj
     * @since 2019/5/21 9:49
     * @param id
     * @return org.springframework.http.ResponseEntity
     */
    public static ResponseEntity dealData(String key, Object id) {

        if (!JudgeUtils.isEmpty(key) && id != null) {

            Map<String, Object> data = new ConcurrentHashMap<>();
            data.put("id", id);

            return ResultUtils.asserts(data);

        } else {

            return ResultUtils.failure();

        }

    }

}
