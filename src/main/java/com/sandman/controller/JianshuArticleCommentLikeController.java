package com.sandman.controller;

import com.sandman.pojo.JianshuArticleCommentLike;
import com.sandman.service.JianshuArticleCommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.19 20:00
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.19 20:00
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuArticleCommentLikeController {

    @Autowired
    private JianshuArticleCommentLikeService jianshuArticleCommentLikeService;

    @PostMapping("article/comment/like")
    public int like(@RequestBody JianshuArticleCommentLike record){
        record.setCreateTime(new Date());
        jianshuArticleCommentLikeService.insertSelective(record);
        return  record.getId();
    }

    @DeleteMapping("article/comment/like")
    public int unLike(Integer likedId){
        return jianshuArticleCommentLikeService.deleteByPrimaryKey(likedId);

    }

}
