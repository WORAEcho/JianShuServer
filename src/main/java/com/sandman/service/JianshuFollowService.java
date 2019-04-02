package com.sandman.service;

import com.sandman.pojo.JianshuFollow;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 04:33
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 04:33
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuFollowService {
    int insertSelective(JianshuFollow record);

    List<Integer> selectFans(Integer userId);

    List<Integer> selectFollwedByUserId(Integer userId);

    int unfollow (Integer userId,Integer followUserId);
}
