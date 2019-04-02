package com.sandman.service;

import com.sandman.pojo.JianshuArticleCommentLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.19 19:58
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.19 19:58
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuArticleCommentLikeService {

//    List<Map<String,Object>> selectLikeCount(List<Integer> commentIdList);

//    List<Map<String,Object>> selectLikeCountWithUserId(List<Integer> commentIdList, Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(JianshuArticleCommentLike record);

    int insertSelective(JianshuArticleCommentLike record);

    JianshuArticleCommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JianshuArticleCommentLike record);

    int updateByPrimaryKey(JianshuArticleCommentLike record);
}
