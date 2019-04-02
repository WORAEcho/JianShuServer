package com.sandman.service;

import com.alibaba.fastjson.JSONObject;
import com.sandman.pojo.JianshuProfile;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.20 21:24
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.20 21:24
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface JianshuProfileService {
    JSONObject getAllProfile(Integer id);
}
