package com.sandman.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandman.dao.*;
import com.sandman.pojo.JianshuProfile;
import com.sandman.pojo.JianshuUser;
import com.sandman.service.JianshuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.12 15:00
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.12 15:00
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuUserServiceImpl implements JianshuUserService {

    @Autowired
    JianshuUserMapper jianshuUserMapper;
    @Autowired
    JianshuFollowMapper jianshuFollowMapper;
    @Autowired
    JianshuProfileMapper jianshuProfileMapper;
    @Autowired
    JianshuArticleMapper jianshuArticleMapper;
    @Autowired
    JianshuArticleLikeMapper jianshuArticleLikeMapper;

    @Override
    public int insertSelective(JianshuUser record) {
        return jianshuUserMapper.insertSelective(record);
    }

    @Override
    public JianshuUser selectByPrimaryKey(Integer id) {
        return jianshuUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public JianshuUser selectByUsername(String  username) {
            return  jianshuUserMapper.selectByUsername(username);
    }

    @Override
    public PageInfo<JianshuUser> selectUsersByRole(String role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JianshuUser> userList = jianshuUserMapper.selectUsersByRole(role);
        PageInfo<JianshuUser> pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    @Override
    public List<Map<String,String>> selectWriters(Integer userId, Boolean unfollowed, Integer pageNum, Integer pageSize) {
        List<Integer> followedIdList = null;
        if(userId != null && unfollowed) {
            followedIdList = jianshuFollowMapper.selectFollwedByUserId(userId);
            followedIdList.add(userId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<JianshuProfile> writerList = jianshuProfileMapper.selectUnfollowedIdList(followedIdList);

        List<Integer> writerIdList = new LinkedList<>();
        writerList.forEach(e->writerIdList.add(e.getUserId()));

        List<Map<String,Object>> likeMap = jianshuArticleLikeMapper.selectTotalLikeByUserIdList(writerIdList);
        List<Map<String,Object>> wordMap = jianshuArticleMapper.selectTotalWordCountByUserIdList(writerIdList);

        List<Map<String,String>> result = new LinkedList<>();

        writerList.forEach(e->{
            Map<String,String> itemMap = new HashMap<>();
            itemMap.put("nickname",e.getNickname());
            itemMap.put("avatarImg",e.getAvatarImg());
            likeMap.forEach(x->{
                if(x.get("userId").equals(e.getUserId())){
                    String likeNum = x.get("totalLike").toString();
                    itemMap.put("likeNum",likeNum);
                }
            });
            if(!itemMap.containsKey("likeNum")){
                itemMap.put("likeNum","0");
            }

            wordMap.forEach((x->{
                if(x.get("userId").equals(e.getUserId())){
                    String wordCount = x.get(("wordCount")).toString();
                    itemMap.put("wordCount",wordCount);
                }
            }));
            if(!itemMap.containsKey("wordCount")){
                itemMap.put("wordCount","0");
            }

            itemMap.put("writerId",e.getUserId().toString());
            result.add(itemMap);
        });

        Integer totalWordCount = 0;
        List<Integer> wordCountList = new LinkedList<>();
        jianshuArticleMapper.selectAllPublishedArticle(userId).forEach(e->wordCountList.add(e.getWordCount()));
        for (Integer integer : wordCountList) {
            totalWordCount = totalWordCount+integer;
        }

        return result;

    }

    @Override
    public List<JianshuProfile> selectUserWithProfile(Integer userId, String role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JianshuUser> userList=jianshuUserMapper.selectUsersByRole(role);
        PageInfo<List<JianshuUser>> pageInfo = new PageInfo(userList);
        List<Integer> userIdList = new LinkedList<>();
        List<JianshuProfile> userProfileList=null;
        if (userList != null && userList.size()>0) {
            userList.stream().forEach(e -> userIdList.add(e.getId()));
            userProfileList = jianshuProfileMapper.selectByUserIdList(userIdList);
        }
        return userProfileList;
    }

    @Override
    public JSONObject selectWriterSurvey(Integer userId){
        Integer fansNum = jianshuFollowMapper.selectFans(userId).size();
        Integer totalWordCount = 0;
        List<Integer> wordCountList = new LinkedList<>();
        jianshuArticleMapper.selectAllPublishedArticle(userId).forEach(e->wordCountList.add(e.getWordCount()));
        List<Integer> publishedArticleIdList = jianshuArticleMapper.selectAllPublishedArticleId(userId);
        Integer totalLikeCount = jianshuArticleLikeMapper.selectTotalLikeByArticleIdList(publishedArticleIdList);
        for (Integer integer : wordCountList) {
            totalWordCount = totalWordCount+integer;
        }
        JSONObject json = new JSONObject();
        json.put("fansNum",fansNum == null ? 0 :fansNum);
        json.put("totalWordCount",totalWordCount == null ? 0 :totalWordCount);
        json.put("totalLikeCount",totalLikeCount == null ? 0 :totalLikeCount);
        return json;
    }

    @Override
    public List<Map<String, Object>> selectSurveyByIdList(String fuzzyKey) {
        return jianshuUserMapper.selectSurveyByIdList(fuzzyKey);
    }
}

