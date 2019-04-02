package com.sandman.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandman.pojo.JianshuProfile;
import com.sandman.pojo.JianshuUser;
import com.sandman.service.JianshuArticleService;
import com.sandman.service.JianshuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.12 14:55
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.12 14:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuUserController {

    @Autowired
    private JianshuUserService jianshuUserService;
    @Autowired
    private JianshuArticleService jianshuArticleService;

    @PostMapping("register")
    public Integer register(@RequestBody JianshuUser record){
        record.setRegisterTime(new Date());
        record.setRole("user");
        record.setStatus(1);
        try {
            jianshuUserService.insertSelective(record);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @GetMapping("username/{id}")
    public Integer existUsername(@PathVariable("id") String username){
        JianshuUser user = jianshuUserService.selectByUsername(username);
        if(user!=null){
            return 1;
        }else {
            return 2;
        }
    }

    @PostMapping("login2")
    public Integer login(@RequestBody JianshuUser record) {
        try{
            JianshuUser user = jianshuUserService.selectByUsername(record.getUsername());
            if (user.getPassword().equals(record.getPassword())){
                return 1;
            }else{
                return 3;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
    }

    @GetMapping("user-info")
    public JianshuUser getUserInfo(String userName) {
        JianshuUser user = jianshuUserService.selectByUsername(userName);
        return user;
    }

    @GetMapping("writers")
    public  List<Map<String,String>> selectWrites(Integer userId ,Boolean unFollowed, Integer pageNum, Integer pageSize) {
        List<Map<String,String>> result = jianshuUserService.selectWriters(userId,unFollowed,pageNum,pageSize);
        return  result;
    }

    @GetMapping("users-profile")
    public List<JianshuProfile> selectUserWithProfile(Integer userId, String role, Integer pageNum, Integer pageSize){
        List<JianshuProfile> list = jianshuUserService.selectUserWithProfile(userId,role,pageNum,pageSize);
        return  list;
    }

    @GetMapping("writer/{writerId}/survey")
    public JSONObject selectWriterSurvey(@PathVariable("writerId") Integer writerId) {
        return jianshuUserService.selectWriterSurvey(writerId);
    }

    @GetMapping("writer/survey")
    public PageInfo<Map<String,Object>> fuzzySelectSurveyByIdList(String fuzzyKey,Integer pageNum,Integer pageSize) {
        String newFuzzyKey = "%"+fuzzyKey+"%";
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = jianshuUserService.selectSurveyByIdList(newFuzzyKey);
        PageInfo<Map<String,Object>> result = new PageInfo<>(list);
        return result;
    }

//    @GetMapping("xxx")
//    public List<JianshuUser> selectUsersByRole(String role,Integer pageNum,Integer pageSize) {
//        List<JianshuUser> list = jianshuUserService.selectUsersByRole(role,pageNum,pageSize);
//
//        return  list;
//    }

}
