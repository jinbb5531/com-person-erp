package com.person.erp.analyze.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itexplore.core.api.utils.ResultUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.entity.RelationVO;
import net.sf.ehcache.search.expression.Or;
import netscape.javascript.JSObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity initEchars(Long startDate, Long endDate){
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
        if (res != null){
           return ResultUtils.success(res);
        }else {
            return ResultUtils.success();
        }
    }
    @GetMapping("/yield")
    public ResponseEntity realYield(Long startDate, Long endDate){
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
        if (res != null){
            return ResultUtils.success(res);
        }else {
            return ResultUtils.success();
        }
    }

    /**
     * 获取订单的利润
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/profit")
    public ResponseEntity profit(Long startDate, Long endDate){
        JSONArray res = new JSONArray();
        if(startDate == null){
            startDate = new Date().getTime() - 7 * 24 * 3600 * 1000;
        }else{
            startDate = startDate/1000;
        }
        if (endDate == null){
            endDate = new Date().getTime()/1000;
        }else {
            endDate = endDate/1000;
        }
        List<RelationVO> profit = anlayzeService.getProfit(startDate, endDate, TokenUtils.getUser().getSystemTag());
        return ResultUtils.success(profit);
    }
}
