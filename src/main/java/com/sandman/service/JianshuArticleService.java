package com.sandman.service;

import com.sandman.pojo.JianshuArticle;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.16 16:39
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.16 16:39
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuArticleService {

    List<Map<String,Object>> selectAllPublishedArticleWithArticleProfile();

    Map<String,Object> selectPublishedArticleWithArticleProfile(Integer articleId);

    List<JianshuArticle> selectAllPublishedArticle(Integer userId);

    List<Map<String,Object>> selectAllPublishedArticleWithArticleProfileByUserId(Integer userId);

    List<Map<String,Object>> selectArticleWithArticleProfileByFuzzyQuery(String fuzzyKey);

    int insertSelective(JianshuArticle record);

    JianshuArticle selectByPrimaryKey(Integer id);

    JianshuArticle selectContentByPrimaryKey(Integer id);

    List<JianshuArticle> selectByCollectionId(Integer collectionId);

    int updateByPrimaryKeySelectiveWithBLOBs(JianshuArticle record);

    int deleteByPrimaryKey(Integer id);

    int publishArticle(Integer articleId);

    int disPublishArticle(Integer articleId);

    List<JianshuArticle> selectPublished(Integer userId);

    List<Integer> selectPublishedId(Integer userId);
}
