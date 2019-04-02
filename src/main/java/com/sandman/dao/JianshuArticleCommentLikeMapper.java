package com.sandman.dao;

import com.sandman.pojo.JianshuArticleCommentLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JianshuArticleCommentLikeMapper {
    List<Map<String,Object>> selectLikeCount(List<Integer> commentIdList);

    List<Map<String,Object>> selectLikeCountWithUserId(@Param("list") List<Integer> commentIdList, Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(JianshuArticleCommentLike record);

    int insertSelective(JianshuArticleCommentLike record);

    JianshuArticleCommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JianshuArticleCommentLike record);

    int updateByPrimaryKey(JianshuArticleCommentLike record);
}