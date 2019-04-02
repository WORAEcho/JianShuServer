package com.sandman.dao;

import com.sandman.pojo.JianshuArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JianshuArticleCommentMapper {

    List<Map<Integer,Integer>> selectAllCommentByArticleId(Integer articleId);

    List<Map<String,Object>> selectCommentWithUser(Integer articleId);

    List<Map<String,Object>> selectReplyWithUser(Integer articleId, @Param("replyIdSet") Set<Integer> replyIdSet);

    int deleteByPrimaryKey(Integer id);

    int insert(JianshuArticleComment record);

    int insertSelective(JianshuArticleComment record);

    JianshuArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JianshuArticleComment record);

    int updateByPrimaryKey(JianshuArticleComment record);
}