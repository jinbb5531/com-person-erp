package com.person.erp.analyze.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itexplore.core.disk.common.utils.DateUtils;
import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> initEchars(Long startDate, Long endDate){
        HashMap res = new HashMap();
        if(startDate == null){
            startDate = new Date().getTime()/1000 - 7 * 24 * 3600;
        }
        if (endDate == null){
            endDate = new Date().getTime()/1000;
        }
        List<Order> planNumber = anlayzeService.getPlanYield(startDate, endDate, 1);
//        List<Order> planNumber = anlayzeService.getPlanYield(startDate, endDate, TokenUtils.getUser().getSystemTag());
        if (planNumber != null && planNumber.size() > 0){
            for (Order order : planNumber) {
                res.put(order.getCutAt().toString(),order.getNumber());
            }
        }
        return res;
    }
    @GetMapping("/yield")
    public Map<String, Object> raalYield(Long startDate, Long endDate){
        HashMap res = new HashMap();
        if(startDate == null){
            startDate = new Date().getTime()/1000 - 7 * 24 * 3600;
        }
        if (endDate == null){
            endDate = new Date().getTime()/1000;
        }
        long sys = 1;
        List<OrderOperate> planNumber = anlayzeService.getRealYield(startDate, endDate, 1);
//        List<OrderOperate> planNumber = anlayzeService.getRealYield(startDate, endDate, TokenUtils.getUser().getSystemTag());
        if (planNumber != null && planNumber.size() > 0){
            for (OrderOperate order : planNumber) {
                res.put(order.getOperaTime().toString(),order.getCutNum());
            }
        }
        return res;
    }
}
