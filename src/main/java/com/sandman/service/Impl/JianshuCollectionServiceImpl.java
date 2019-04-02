package com.sandman.service.Impl;

import com.sandman.dao.JianshuCollectionMapper;
import com.sandman.pojo.JianshuCollection;
import com.sandman.service.JianshuCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.16 16:38
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.16 16:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JianshuCollectionServiceImpl implements JianshuCollectionService {
    @Autowired
    JianshuCollectionMapper jianshuCollectionMapper;

    @Override
    public List<JianshuCollection> selectByUserId(Integer userId) {
        return jianshuCollectionMapper.selectByUserId(userId);
    }

    @Override
    public int insertSelective(JianshuCollection record) {
        return jianshuCollectionMapper.insertSelective(record);
    }

    @Override
    public List<JianshuCollection> selectByCollectionIdList(List<Integer> collectionIdList) {
        return jianshuCollectionMapper.selectByCollectionIdList(collectionIdList);
    }
}
