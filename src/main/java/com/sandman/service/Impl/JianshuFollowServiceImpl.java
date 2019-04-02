package com.sandman.service.Impl;

import com.sandman.dao.JianshuFollowMapper;
import com.sandman.pojo.JianshuFollow;
import com.sandman.service.JianshuFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 04:34
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 04:34
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuFollowServiceImpl implements JianshuFollowService {
    @Autowired
    JianshuFollowMapper jianshuFollowMapper;

    @Override
    public int insertSelective(JianshuFollow record) {
        return jianshuFollowMapper.insertSelective(record);
    }

    @Override
    public List<Integer> selectFans(Integer userId) {
        return jianshuFollowMapper.selectFans(userId);
    }

    @Override
    public List<Integer> selectFollwedByUserId(Integer userId) {
        return jianshuFollowMapper.selectFollwedByUserId(userId);
    }

    @Override
    public int unfollow(Integer userId, Integer followUserId) {
        return  jianshuFollowMapper.unfollow(userId,followUserId);
    }


}
