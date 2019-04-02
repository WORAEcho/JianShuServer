package com.sandman.dao;

import com.sandman.pojo.JianshuArticleLike;

import java.util.List;
import java.util.Map;

public interface JianshuArticleLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JianshuArticleLike record);

    int insertSelective(JianshuArticleLike record);

    JianshuArticleLike selectByPrimaryKey(Integer id);

    List<JianshuArticleLike> selectByArticleId(Integer articleId);

    Integer selectTotalLikeByArticleIdList(List<Integer> articleIdList);

    List<Map<String,Object>> selectEachLikeByArticleIdList(List<Integer> articleIdList);

    List<Map<String,Object>> selectLikeAndCommentTotalByRecommendArticles();

    List<Map<String,Object>> selectTotalLikeByUserIdList(List<Integer> userId);

    int updateByPrimaryKeySelective(JianshuArticleLike record);

    int updateByPrimaryKey(JianshuArticleLike record);

    int deleteByUserIdAndArticleId(Integer articleId, Integer userId);
}