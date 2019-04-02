package com.sandman.service;

import com.sandman.pojo.JianshuCollection;

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
public interface JianshuCollectionService {

    List<JianshuCollection> selectByUserId(Integer userId);

    int insertSelective(JianshuCollection record);

    List<JianshuCollection> selectByCollectionIdList(List<Integer> collectionIdList);

}
