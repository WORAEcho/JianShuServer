package com.sandman.controller;

import com.github.pagehelper.PageInfo;
import com.sandman.service.JianshuSearchKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.10 22:15
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.10 22:15
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
public class JianshuSearchKeyController {

    @Autowired
    private JianshuSearchKeyService jianshuSearchKeyService;

    @GetMapping("hot_search")
    public List<String>  profile(){
        return jianshuSearchKeyService.selectHotSearchKey();
    }

}