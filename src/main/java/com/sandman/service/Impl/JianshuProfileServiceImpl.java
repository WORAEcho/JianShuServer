package com.sandman.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.sandman.dao.JianshuArticleLikeMapper;
import com.sandman.dao.JianshuArticleMapper;
import com.sandman.dao.JianshuFollowMapper;
import com.sandman.dao.JianshuProfileMapper;
import com.sandman.pojo.JianshuArticleLike;
import com.sandman.pojo.JianshuProfile;
import com.sandman.service.JianshuProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 21:24
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 21:24
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuProfileServiceImpl implements JianshuProfileService {

    @Autowired
    JianshuProfileMapper jianshuProfileMapper;
    @Autowired
    JianshuFollowMapper jianshuFollowMapper;
    @Autowired
    JianshuArticleMapper jianshuArticleMapper;
    @Autowired
    JianshuArticleLikeMapper jianshuArticleLikeMapper;


    @Override
    public JSONObject getAllProfile(Integer userId) {
        JSONObject json =new JSONObject();

        List<Integer> followedList = jianshuFollowMapper.selectFollwedByUserId(userId);
        List<Integer> fansList = jianshuFollowMapper.selectFans(userId);
        JianshuProfile jianshuProfile = jianshuProfileMapper.selectByUserId(userId);
        Integer articleNum = jianshuArticleMapper.selectAllPublishedArticle(userId).size();

        json.put("followedNum",followedList.size());
        json.put("fansNum",fansList.size());
        json.put("articleNum",articleNum == null ? 0 : articleNum);
        json.put("profile",jianshuProfile);

        List<Integer> userIdList = new LinkedList<>();
        userIdList.add(userId);

        List<Map<String,Object>> likeMap = jianshuArticleLikeMapper.selectTotalLikeByUserIdList(userIdList);
        likeMap.forEach((x->{
            if(x.get("userId").equals(userId)){
                String wordCount = x.get(("totalLike")).toString();
                json.put("likeNum",wordCount);
            }
        }));
        if(!json.containsKey("likeNum")){
            json.put("likeNum","0");
        }

        List<Map<String,Object>> wordMap = jianshuArticleMapper.selectTotalWordCountByUserIdList(userIdList);
        wordMap.forEach((x->{
            if(x.get("userId").equals(userId)){
                String wordCount = x.get(("wordCount")).toString();
                json.put("wordCount",wordCount);
            }
        }));
        if(!json.containsKey("wordCount")){
            json.put("wordCount","0");
        }

        return json;
    }
}
