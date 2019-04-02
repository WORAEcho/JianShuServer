package com.sandman.service.Impl;

import com.github.pagehelper.PageHelper;
import com.sandman.dao.JianshuArticleCommentLikeMapper;
import com.sandman.dao.JianshuArticleCommentMapper;
import com.sandman.pojo.JianshuArticleComment;
import com.sandman.service.JianshuArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.14 19:51
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.14 19:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuArticleCommentServiceImpl implements JianshuArticleCommentService {
    @Autowired
    JianshuArticleCommentMapper jianshuArticleCommentMapper;

    @Autowired
    JianshuArticleCommentLikeMapper jianshuArticleCommentLikeMapper;

    public Map<Integer,Set<Integer>> getAllChildren(List<Map<Integer, Integer>> totalList,Map<Integer,Set<Integer>> replyChildrenMap){
        totalList.stream().forEach(x->{
            for (Map.Entry<Integer,Set<Integer>> entry : replyChildrenMap.entrySet()) {
                if(entry.getKey().equals(x.get("parent_id"))){
                    entry.getValue().add(x.get("id"));
                }
                if(entry.getValue().contains(x.get("parent_id")) && !entry.getValue().contains(x.get("id"))){
                    entry.getValue().add(x.get("id"));
                    getAllChildren(totalList,replyChildrenMap);
                    //测试:totalList => totalList.stream().filter(e->!entry.getValue().contains(e.get("id")) || x.get("id") == e.get("parent_id")).collect(Collectors.toList())
                }
            }
        });
        return replyChildrenMap;
    }

    public Map<Integer,Set<Integer>> getAllChildrenHelper(Integer articleId,List<Integer> parentIdList){
        List<Map<Integer, Integer>> totalList = jianshuArticleCommentMapper.selectAllCommentByArticleId(articleId);

        Map<Integer,Set<Integer>> replyChildrenMap = new LinkedHashMap<>();
        parentIdList.forEach(e->
        {
            Set<Integer> set=new HashSet<>();
            replyChildrenMap.put(e, set);
        });

        long d1 = System.currentTimeMillis();
        Map<Integer,Set<Integer>> replyChildrenMapResult = getAllChildren(totalList,replyChildrenMap);
        long d2 = System.currentTimeMillis();

        if((d2-d1) > 10){
            System.out.println("获取评论回复量花费时间："+ (d2-d1) + "毫秒");
        }

        return replyChildrenMapResult;
    }



    @Override
    public List<Map<String, Object>> selectCommentWithUser(Integer pageNum,Integer articleId,Integer userId) {
        PageHelper.startPage(pageNum,20);
        List<Map<String, Object>> mainCommentList = jianshuArticleCommentMapper.selectCommentWithUser(articleId);

        if(mainCommentList.size() != 0 && mainCommentList != null){
            List<Integer> parentIdList = new LinkedList<>();

            mainCommentList.forEach(e->parentIdList.add((Integer) e.get("id")));
            Map<Integer,Set<Integer>> replyChildrenMapResult = getAllChildrenHelper(articleId,parentIdList);

            List<Map<String,Object>> likeCountList;
            if(userId.equals(-1)){
                likeCountList = jianshuArticleCommentLikeMapper.selectLikeCount(parentIdList);
            }else{
                likeCountList = jianshuArticleCommentLikeMapper.selectLikeCountWithUserId(parentIdList,userId);
            }

            mainCommentList.forEach(e->{
                Optional<Map<String,Object>> likeMapOpt = likeCountList.stream().filter(x->x.get("comment_id") == e.get("id")).findFirst();
                if(likeMapOpt.isPresent()){
                    e.put("likeTotal",likeMapOpt.get().get("like_total"));
                    e.put("liked",likeMapOpt.get().get("liked"));
                    e.put("likedId",likeMapOpt.get().get("id"));
                }else{
                    e.put("likeTotal",0);
                    e.put("liked",0);
                    e.put("likedId",0);
                }
                e.put("replyCount",replyChildrenMapResult.get(e.get("id")).size());
            });
        }

        return mainCommentList;
    }

    @Override
    public List<Map<String, Object>> selectReplyWithUser(Integer pageNum,Integer articleId, Integer parentId,Integer userId) {
        List<Integer> parentIdList = new LinkedList<>();
        parentIdList.add(parentId);
        Map<Integer,Set<Integer>> replyChildrenMapResult = getAllChildrenHelper(articleId,parentIdList);

        Set<Integer > replyIdSet = replyChildrenMapResult.get(parentId);

        List<Map<String, Object>> resultList = new LinkedList<>();

        if(replyIdSet.size() != 0 && replyIdSet != null) {
            PageHelper.startPage(pageNum, 8);
            resultList = jianshuArticleCommentMapper.selectReplyWithUser(articleId, replyIdSet);

            List<Integer> resultIdList = new LinkedList<>();
            resultList.forEach(e -> resultIdList.add((Integer) e.get("id")));

            List<Map<String, Object>> likeCountList;
            if (userId.equals(-1)) {
                likeCountList = jianshuArticleCommentLikeMapper.selectLikeCount(resultIdList);
            } else {
                likeCountList = jianshuArticleCommentLikeMapper.selectLikeCountWithUserId(resultIdList, userId);
            }
            resultList.forEach(e -> {
                Optional<Map<String, Object>> likeMapOpt = likeCountList.stream().filter(x -> x.get("comment_id").equals(e.get("id"))).findFirst();
                if (likeMapOpt.isPresent()) {
                    e.put("likeTotal", likeMapOpt.get().get("like_total"));
                    e.put("liked", likeMapOpt.get().get("liked"));
                    e.put("likedId", likeMapOpt.get().get("id"));
                } else {
                    e.put("likeTotal", 0);
                    e.put("liked", 0);
                    e.put("likedId", 0);
                }
            });
        }
        return resultList;
    }

    @Override
    public int insertSelective(JianshuArticleComment record) {
        return jianshuArticleCommentMapper.insertSelective(record);
    }

    @Override
    public JianshuArticleComment selectByPrimaryKey(Integer id) {
        return null;
    }
}
