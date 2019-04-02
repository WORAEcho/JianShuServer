package com.sandman.dao;

import com.sandman.pojo.JianshuArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JianshuArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JianshuArticle record);

    int insertSelective(JianshuArticle record);

    JianshuArticle selectContentByPrimaryKey(Integer id);

    JianshuArticle selectByPrimaryKey(Integer id);

    List<JianshuArticle> selectByCollectionId(Integer collectionId);

    List<JianshuArticle> selectPublished(List<Integer> collectionIdList);

    List<Integer> selectPublishedId(List<Integer> collectionIdList);

    List<Map<String,Object>> selectAllPublishedArticleWithArticleProfile();

    Map<String,Object> selectPublishedArticleWithArticleProfile(Integer articleId);

    List<Map<String,Object>> selectAllPublishedArticleWithArticleProfileByUserId(Integer userId);

    List<Map<String,Object>> selectArticleWithArticleProfileByFuzzyQuery(String fuzzyKey);

    List<JianshuArticle> selectAllPublishedArticle(Integer userId);

    List<Integer> selectAllPublishedArticleId(Integer userId);

    List<Map<String,Object>> selectTotalWordCountByUserIdList(List<Integer> userIdList);

    int updateByPrimaryKeySelective(JianshuArticle record);

    int updateByPrimaryKeyWithBLOBs(JianshuArticle record);

    int updateByPrimaryKey(JianshuArticle record);

    int publishArticle(Integer articleId);

    int disPublishArticle(Integer articleId);
}