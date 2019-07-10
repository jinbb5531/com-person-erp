package com.person.erp.analyze.controller;

import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.order.dao.IOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 报表分析模块
 */
@RestController
@RequestMapping("/api/anlayze")
public class AnalyzeController {

    @Resource
    IAnlayzeService anlayzeService;
    /**
     * 初始化报表
     */
    @GetMapping("/init")
    public void initEchars(Long startDate, Long endDate){
        if(startDate != null){

        }
        anlayzeService.getPlanNumber(startDate, endDate);
    }
}
