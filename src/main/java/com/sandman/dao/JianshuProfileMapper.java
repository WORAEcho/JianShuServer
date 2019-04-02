package com.sandman.dao;

import com.sandman.pojo.JianshuProfile;
import com.sandman.pojo.JianshuUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface JianshuProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JianshuProfile record);

    int insertSelective(JianshuProfile record);

    JianshuProfile selectByPrimaryKey(Integer id);

    JianshuProfile selectByUserId(Integer userId);

    List<JianshuProfile> selectByUserIdList(@Param("list") List<Integer> userIdList);

    List<JianshuProfile> selectUnfollowedIdList(List<Integer> unfollowedIdList);

    int updateByPrimaryKeySelective(JianshuProfile record);

    int updateByPrimaryKey(JianshuProfile record);
}