package com.sandman.controller;

import com.alibaba.fastjson.JSONObject;
import com.sandman.service.JianshuProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 21:27
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 21:27
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuProfileController {

    @Autowired
    private JianshuProfileService jianshuProfileService;

    @GetMapping("profile-all")
    public JSONObject profile(Integer userId){
        return jianshuProfileService.getAllProfile(userId);
    }

}