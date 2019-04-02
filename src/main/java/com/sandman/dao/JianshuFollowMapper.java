package com.sandman.dao;

import com.sandman.pojo.JianshuFollow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface JianshuFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int unfollow (Integer userId,Integer followUserId);

    int insert(JianshuFollow record);

    int insertSelective(JianshuFollow record);

    JianshuFollow selectByPrimaryKey(Integer id);

    List<Integer> selectFollwedByUserId(Integer userId);

    List<Integer> selectFans(Integer userId);

    int updateByPrimaryKeySelective(JianshuFollow record);

    int updateByPrimaryKey(JianshuFollow record);
}