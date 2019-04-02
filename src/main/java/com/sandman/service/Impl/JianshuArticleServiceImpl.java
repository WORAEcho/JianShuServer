package com.sandman.service.Impl;

import com.sandman.dao.JianshuArticleMapper;
import com.sandman.dao.JianshuCollectionMapper;
import com.sandman.pojo.JianshuArticle;
import com.sandman.service.JianshuArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.16 16:39
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.16 16:39
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuArticleServiceImpl implements JianshuArticleService {

    @Autowired
    JianshuArticleMapper jianshuArticleMapper;
    @Autowired
    JianshuCollectionMapper jianshuCollectionMapper;


    @Override
    public List<Map<String,Object>> selectAllPublishedArticleWithArticleProfile() {
        return jianshuArticleMapper.selectAllPublishedArticleWithArticleProfile();
    }

    @Override
    public Map<String, Object> selectPublishedArticleWithArticleProfile(Integer articleId){
        return jianshuArticleMapper.selectPublishedArticleWithArticleProfile(articleId);
    }

    @Override
    public List<JianshuArticle> selectAllPublishedArticle(Integer userId) {
        return jianshuArticleMapper.selectAllPublishedArticle(userId);
    }

    @Override
    public List<Map<String, Object>> selectAllPublishedArticleWithArticleProfileByUserId(Integer userId) {
        return jianshuArticleMapper.selectAllPublishedArticleWithArticleProfileByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> selectArticleWithArticleProfileByFuzzyQuery(String fuzzyKey) {
        return jianshuArticleMapper.selectArticleWithArticleProfileByFuzzyQuery(fuzzyKey);
    }

    @Override
    public int insertSelective(JianshuArticle record) {
        return jianshuArticleMapper.insertSelective(record);
    }

    @Override
    public JianshuArticle selectByPrimaryKey(Integer id) {
        return jianshuArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public JianshuArticle selectContentByPrimaryKey(Integer id) {
        return jianshuArticleMapper.selectContentByPrimaryKey(id);
    }

    @Override
    public List<JianshuArticle> selectByCollectionId(Integer collectionId) {
        return jianshuArticleMapper.selectByCollectionId(collectionId);
    }

    @Override
    public int updateByPrimaryKeySelectiveWithBLOBs(JianshuArticle record) {
        return jianshuArticleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jianshuArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int publishArticle(Integer articleId) {
        return jianshuArticleMapper.publishArticle(articleId);
    }

    @Override
    public int disPublishArticle(Integer articleId) {
        return jianshuArticleMapper.disPublishArticle(articleId);
    }

    @Override
    public List<JianshuArticle> selectPublished(Integer userId) {
        List<Integer> collectionIdList=jianshuCollectionMapper.selectCollectionIdByUserId(userId);
        return jianshuArticleMapper.selectPublished(collectionIdList);
    }

    @Override
    public List<Integer> selectPublishedId(Integer userId) {
        List<Integer> collectionIdList=jianshuCollectionMapper.selectCollectionIdByUserId(userId);
        return jianshuArticleMapper.selectPublishedId(collectionIdList);
    }

}
