package com.person.erp.analyze;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表分析模块
 */
@RestController
@RequestMapping("/api/anlayze")
public class AnalyzeController {

    /**
     * 初始化报表
     */
    @GetMapping("/init")
    public void initEchars(){

    }
}
