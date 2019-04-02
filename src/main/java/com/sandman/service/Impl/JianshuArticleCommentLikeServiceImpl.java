package com.sandman.service.Impl;

import com.sandman.dao.JianshuArticleCommentLikeMapper;
import com.sandman.pojo.JianshuArticleCommentLike;
import com.sandman.service.JianshuArticleCommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.19 19:58
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.19 19:58
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@Service
public class JianshuArticleCommentLikeServiceImpl implements JianshuArticleCommentLikeService {
    @Autowired
    JianshuArticleCommentLikeMapper jianshuArticleCommentLikeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jianshuArticleCommentLikeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JianshuArticleCommentLike record) {
        return jianshuArticleCommentLikeMapper.insert(record);
    }

    @Override
    public int insertSelective(JianshuArticleCommentLike record) {
        return jianshuArticleCommentLikeMapper.insertSelective(record);
    }

    @Override
    public JianshuArticleCommentLike selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(JianshuArticleCommentLike record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JianshuArticleCommentLike record) {
        return 0;
    }
}
