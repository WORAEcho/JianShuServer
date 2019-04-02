package com.sandman.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sandman.pojo.JianshuProfile;
import com.sandman.pojo.JianshuUser;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.12 14:59
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.12 14:59
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

public interface JianshuUserService {
    int insertSelective(JianshuUser record);

    JianshuUser selectByPrimaryKey(Integer id);

    JianshuUser selectByUsername(String username);

    PageInfo<JianshuUser> selectUsersByRole(String role, Integer pageNum, Integer pageSize);

    List<Map<String,String>> selectWriters(Integer userId, Boolean unfollowed, Integer pageNum, Integer pageSize);

    List<JianshuProfile> selectUserWithProfile(Integer userId, String role, Integer pageNum, Integer pageSize);

    JSONObject selectWriterSurvey(Integer userId);

    List<Map<String,Object>> selectSurveyByIdList(String fuzzyKey);

}
