package com.sandman.controller;

import com.alibaba.fastjson.JSONObject;
import com.sandman.pojo.JianshuCollection;
import com.sandman.pojo.JianshuUser;
import com.sandman.service.JianshuCollectionService;
import com.sandman.service.JianshuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.16 16:41
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.16 16:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuCollectionController {

    @Autowired
    private JianshuCollectionService jianshuCollectionService;
    @Autowired
    private  JianshuUserService jianshuUserService;
    @GetMapping("collection")
    public List<JianshuCollection> getCollection(@RequestParam String user){
        try {
            Integer userId = jianshuUserService.selectByUsername(user).getId();
            List<JianshuCollection> collections=jianshuCollectionService.selectByUserId(userId);
            return collections;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("collection")
    public int newCollection(@RequestBody String jsonStr){
        try {
            JSONObject json = JSONObject.parseObject(jsonStr);
            String userName=(String)json.get("user");
            String collectionName=(String)json.get("collectionName");
            Integer userId = jianshuUserService.selectByUsername(userName).getId();
            JianshuCollection record =new JianshuCollection();
            record.setCollectionName(collectionName);
            record.setUserId(userId);
            record.setCreateTime(new Date());
            record.setStatus(1);
            return jianshuCollectionService.insertSelective(record);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

