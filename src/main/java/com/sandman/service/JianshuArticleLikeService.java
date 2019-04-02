package com.sandman.service;

import com.sandman.pojo.JianshuArticleLike;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.24 22:09
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.24 22:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuArticleLikeService {
    int insertSelective(JianshuArticleLike record);

    int deleteByUserIdAndArticleId(Integer articleId, Integer userId);

    List<JianshuArticleLike> selectByArticleId(Integer articleId);

    Integer selectTotalLikeByArticleIdList(List<Integer> articleIdList);

    List<Map<String,Object>> selectEachLikeByArticleIdList(List<Integer> articleIdList);

    List<Map<String,Object>> selectLikeAndCommentTotalByRecommendArticles();
}
