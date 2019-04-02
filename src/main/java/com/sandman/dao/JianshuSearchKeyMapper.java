package com.sandman.dao;

import com.sandman.pojo.JianshuSearchKey;

import java.util.List;
import java.util.Map;

public interface JianshuSearchKeyMapper {

    List<String> selectHotSearchKey();

    int deleteByPrimaryKey(Integer id);

    int insert(JianshuSearchKey record);

    int insertSelective(JianshuSearchKey record);

    JianshuSearchKey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JianshuSearchKey record);

    int updateByPrimaryKey(JianshuSearchKey record);
}