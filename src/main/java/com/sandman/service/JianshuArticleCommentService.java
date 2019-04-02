package com.sandman.service;

import com.sandman.pojo.JianshuArticleComment;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.14 19:51
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.14 19:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuArticleCommentService {
    List<Map<String,Object>> selectCommentWithUser(Integer pageNum,Integer articleId,Integer userId);

    List<Map<String,Object>> selectReplyWithUser(Integer pageNum,Integer articleId,Integer parentId,Integer userId);

    int insertSelective(JianshuArticleComment record);

    JianshuArticleComment selectByPrimaryKey(Integer id);

}
