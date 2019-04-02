package com.sandman.service.Impl;

import com.github.pagehelper.PageHelper;
import com.sandman.dao.JianshuSearchKeyMapper;
import com.sandman.pojo.JianshuSearchKey;
import com.sandman.service.JianshuSearchKeyService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.10 20:51
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.10 20:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@Service
public class JianshuSearchKeyServiceImpl implements JianshuSearchKeyService {

    @Autowired
    JianshuSearchKeyMapper jianshuSearchKeyMapper;

    @Override
    public List<String>  selectHotSearchKey() {
        return jianshuSearchKeyMapper.selectHotSearchKey();
    }

    @Override
    public int insert(JianshuSearchKey record) {
        return jianshuSearchKeyMapper.insert(record);
    }
}
