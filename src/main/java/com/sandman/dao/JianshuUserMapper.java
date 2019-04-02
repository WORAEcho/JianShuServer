package com.sandman.dao;
import com.sandman.pojo.JianshuProfile;
import com.sandman.pojo.JianshuUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JianshuUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JianshuUser record);

    int insertSelective(JianshuUser record);

    JianshuUser selectByPrimaryKey(Integer id);

    JianshuUser selectByUsername(String username);

    List<JianshuUser> selectUsersByRole(String role);

    List<Map<String,Object>> selectSurveyByIdList(String fuzzyKey);

    List<JianshuUser> selectUnfollowedWriters(List<Integer> list);

    int updateByPrimaryKeySelective(JianshuUser record);

    int updateByPrimaryKeyWithBLOBs(JianshuUser record);

    int updateByPrimaryKey(JianshuUser record);
}