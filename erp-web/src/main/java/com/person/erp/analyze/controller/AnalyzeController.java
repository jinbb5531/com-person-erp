package com.person.erp.analyze.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import net.sf.ehcache.search.expression.Or;
import netscape.javascript.JSObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 报表分析模块
 */
@RestController
@RequestMapping("/api/anlayze")
public class AnalyzeController {

    @Resource
    IAnlayzeService anlayzeService;
    /**
     * 初始化标准产量
     */
    @GetMapping("/init")
    public JSONArray initEchars(Long startDate, Long endDate){
        JSONArray res = new JSONArray();
        if(startDate == null){
            startDate = new Date().getTime()/1000 - 7 * 24 * 3600;
        }else{
            startDate = startDate/1000;
        }
        if (endDate == null){
            endDate = new Date().getTime()/1000;
        }else{
            endDate = endDate/1000;
        }
        List<Order> planNumber = anlayzeService.getPlanYield(startDate, endDate, TokenUtils.getUser().getSystemTag());
        if (planNumber != null && planNumber.size() > 0){
            for (Order order : planNumber) {
                JSONObject json = new JSONObject();
                json.put(order.getCutAt().toString(),order.getNumber());
                res.add(json);
            }
        }
        return res;
    }
    @GetMapping("/yield")
    public JSONArray raalYield(Long startDate, Long endDate){
        JSONArray res = new JSONArray();
        if(startDate == null){
            startDate = new Date().getTime()/1000 - 7 * 24 * 3600;
        }else {
            startDate = startDate/1000;
        }
        if (endDate == null){
            endDate = new Date().getTime()/1000;
        }else {
            endDate = endDate/1000;
        }
        List<OrderOperate> planNumber = anlayzeService.getRealYield(startDate, endDate, TokenUtils.getUser().getSystemTag());
        if (planNumber != null && planNumber.size() > 0){
            for (OrderOperate order : planNumber) {
                JSONObject json = new JSONObject();
                json.put(order.getOperaTime().toString(),order.getCutNum());
                res.add(json);
            }
        }
        return res;
    }
}
