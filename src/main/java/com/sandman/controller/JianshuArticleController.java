package com.sandman.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandman.pojo.JianshuArticle;
import com.sandman.pojo.JianshuCollection;
import com.sandman.pojo.JianshuSearchKey;
import com.sandman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.16 16:41
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.16 16:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuArticleController {

    @Autowired
    private JianshuArticleService jianshuArticleService;
    @Autowired
    private JianshuArticleLikeService jianshuArticleLikeService;
    @Autowired
    private JianshuSearchKeyService jianshuSearchKeyService;

    //个人主页
    @GetMapping("/user/{id}/article/published/profile")
    public PageInfo<List<Map<String,Object>>> getAllPublishedArticleWithArticleProfileByUserId(@PathVariable("id") Integer userId,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String,Object>> list = jianshuArticleService.selectAllPublishedArticleWithArticleProfileByUserId(userId);
        PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    //主页
    @GetMapping("article/published/profile")
    public PageInfo<List<Map<String,Object>>> getAllPublishedArticleWithArticleProfile(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String,Object>> publishedArticleWithArticleProfileList = jianshuArticleService.selectAllPublishedArticleWithArticleProfile();

        List<Map<String,Object>> likeNumList = jianshuArticleLikeService.selectLikeAndCommentTotalByRecommendArticles();

        publishedArticleWithArticleProfileList.forEach(e->{
            likeNumList.forEach(x->{
                if(e.get("articleId").equals(x.get("id"))){
                    e.put("likeNum",x.get("likeNum"));
                    e.put("commentTotal",x.get("commentTotal"));
                }
            });
        });

        PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo(publishedArticleWithArticleProfileList);
        return pageInfo;
    }

    //模糊查询
    @GetMapping("article/published/profile/fuzzy")
    public PageInfo<List<Map<String,Object>>> getArticleWithArticleProfileByFuzzyQuery(String fuzzyKey,Integer pageNum, Integer pageSize){
        //把关键字加入热门搜索表
        JianshuSearchKey record = new JianshuSearchKey();
        record.setCreateTime(new Date());
        record.setKeyword(fuzzyKey);
        jianshuSearchKeyService.insert(record);

        String newFuzzyKey = "%"+fuzzyKey+"%";
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String,Object>> publishedArticleWithArticleProfileList = jianshuArticleService.selectArticleWithArticleProfileByFuzzyQuery(newFuzzyKey);

        List<Integer> articleIdList = new LinkedList<>();
        publishedArticleWithArticleProfileList.forEach(e->articleIdList.add((Integer) e.get("articleId")));

        List<Map<String,Object>> likeNumList = jianshuArticleLikeService.selectEachLikeByArticleIdList(articleIdList);

        publishedArticleWithArticleProfileList.forEach(e->{
            likeNumList.forEach(x->{
                if(e.get("articleId").equals(x.get("article_id"))){
                    e.put("likeNum",x.get("likeNum"));
                }
            });
            if(!e.containsKey("likeNum")){
                e.put("likeNum","0");
            }
        });

        PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo(publishedArticleWithArticleProfileList);
        return pageInfo;
    }

    //文章页面
    @GetMapping("article/{articleId}/published/profile")
    public Map<String,Object> getPublishedArticleWithArticleProfile(@PathVariable("articleId") Integer articleId){
        Map<String,Object> publishedArticleWithArticleProfile = jianshuArticleService.selectPublishedArticleWithArticleProfile(articleId);
        return publishedArticleWithArticleProfile;
    }

    @GetMapping("article")
    public List<JianshuArticle> getArticleByCollectionId(@RequestParam Integer collectionId) {
        return jianshuArticleService.selectByCollectionId(collectionId);
    }

    @DeleteMapping("article")
    public int deleteArticleByArticleId(@RequestParam Integer articleId) {
        return jianshuArticleService.deleteByPrimaryKey(articleId);
    }

    @GetMapping("article_content")
    public JianshuArticle getArticleContentById(@RequestParam Integer articleId) {
        return jianshuArticleService.selectContentByPrimaryKey(articleId);
    }

    // 下面的方法都是针对用户的
    @PostMapping("new_article")
    public int newArticle(@RequestBody JianshuArticle newArticle) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        newArticle.setCreateTime(date);
        newArticle.setPublished(0);
        newArticle.setStatus(1);
        newArticle.setTitle(dateString);
        newArticle.setUpdateTime(date);
        return jianshuArticleService.insertSelective(newArticle);
    }

    @PutMapping("article")
    public int saveArticle(@RequestBody JianshuArticle newArticle) {
        try {
            if(newArticle.getId()==null || "".equals(newArticle.getId())){
                return 0;
            }else {
                //为了避免负数的产生
                jianshuArticleService.updateByPrimaryKeySelectiveWithBLOBs(newArticle);
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @PutMapping("article/published")
    public int publishArticle(Integer articleId) {
        try {
            return jianshuArticleService.publishArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @PutMapping("article/dispublish")
    public int disPublishArticle(Integer articleId) {
        try {
            return jianshuArticleService.disPublishArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping("article/published")
    public List<JianshuArticle> getPublishedArticles(Integer userId) {
        try {
            return jianshuArticleService.selectPublished(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("article/published/id")
    public List<Integer> getPublishedArticlesId(Integer userId) {
        try {
            return jianshuArticleService.selectPublishedId(userId);
        } catch (Exception e) {
            return null;
        }
    }

//
//    @GetMapping("ccc")
//    public List<JianshuArticle> getArticleByCollectionIds(@RequestParam List<Integer> userIds) {
//    }
}
