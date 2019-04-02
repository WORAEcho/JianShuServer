package com.sandman.dao;

import com.sandman.pojo.JianshuCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JianshuCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JianshuCollection record);

    int insertSelective(JianshuCollection record);

    JianshuCollection selectContentByPrimaryKey(Integer id);

    List<JianshuCollection> selectByCollectionIdList(List<Integer> collectionIdList);

    List<JianshuCollection> selectByUserId(Integer userId);

    List<Integer> selectCollectionIdByUserId(Integer userId);

    int updateByPrimaryKeySelective(JianshuCollection record);

    int updateByPrimaryKey(JianshuCollection record);
}