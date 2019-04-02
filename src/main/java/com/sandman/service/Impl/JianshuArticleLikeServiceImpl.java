package com.sandman.service.Impl;

import com.sandman.dao.JianshuArticleLikeMapper;
import com.sandman.pojo.JianshuArticleLike;
import com.sandman.service.JianshuArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.24 22:10
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.24 22:10
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuArticleLikeServiceImpl implements JianshuArticleLikeService {
    @Autowired
    JianshuArticleLikeMapper jianshuArticleLikeMapper;

    @Override
    public int insertSelective(JianshuArticleLike record) {
        return jianshuArticleLikeMapper.insertSelective(record);
    }

    @Override
    public int deleteByUserIdAndArticleId(Integer articleId, Integer userId) {
        return jianshuArticleLikeMapper.deleteByUserIdAndArticleId(articleId,userId);
    }

    @Override
    public List<JianshuArticleLike> selectByArticleId(Integer articleId) {
        return jianshuArticleLikeMapper.selectByArticleId(articleId);
    }

    @Override
    public Integer selectTotalLikeByArticleIdList(List<Integer> articleIdList) {
        return jianshuArticleLikeMapper.selectTotalLikeByArticleIdList(articleIdList);
    }

    @Override
    public List<Map<String, Object>> selectEachLikeByArticleIdList(List<Integer> articleIdList) {
        return jianshuArticleLikeMapper.selectEachLikeByArticleIdList(articleIdList);
    }

    @Override
    public List<Map<String, Object>> selectLikeAndCommentTotalByRecommendArticles() {
        return jianshuArticleLikeMapper.selectLikeAndCommentTotalByRecommendArticles();
    }
}
