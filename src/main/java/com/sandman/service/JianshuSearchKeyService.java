package com.sandman.service;

import com.sandman.pojo.JianshuSearchKey;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.10 20:50
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.10 20:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuSearchKeyService {
    List<String>  selectHotSearchKey();

    int insert(JianshuSearchKey record);
}
