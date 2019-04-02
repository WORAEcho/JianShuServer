package com.sandman.controller;

import com.alibaba.fastjson.JSONObject;
import com.sandman.pojo.JianshuCollection;
import com.sandman.pojo.JianshuFollow;
import com.sandman.service.JianshuCollectionService;
import com.sandman.service.JianshuFollowService;
import com.sandman.service.JianshuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 04:37
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 04:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */


@CrossOrigin
@RestController
public class JianshuFollowController {

    @Autowired
    private JianshuFollowService jianshuFollowService;

//    @GetMapping("profile-all")
//    public JSONObject profile(Integer userId){
//        List<Integer> followedList = jianshuFollowService.selectFollwedByUserId(userId);
//        List<Integer> fansList = jianshuFollowService.selectFans(userId);
//        JSONObject json =new JSONObject();
//        json.put("followedNum",followedList.size());
//        json.put("fansNum",fansList.size());
//        return json;
//    }
    @GetMapping("follower")
    public List<Integer> getfollow(Integer userId) {
        return jianshuFollowService.selectFollwedByUserId(userId);
    }


    @PostMapping("follower")
    public Integer follow(@RequestBody JianshuFollow record) {
        Date date = new Date();
        record.setFollowDate(date);
        record.setStatus(1);
        record.setUpdateDate(date);
        return jianshuFollowService.insertSelective(record);
    }

    @DeleteMapping("follower")
    public Integer unfollow(Integer userId,Integer followUserId) {
        return jianshuFollowService.unfollow(userId,followUserId);
    }
}