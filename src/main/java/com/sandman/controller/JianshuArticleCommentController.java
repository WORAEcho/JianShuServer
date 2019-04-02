package com.sandman.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandman.pojo.JianshuArticleComment;
import com.sandman.service.JianshuArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.03.14 19:51
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.03.14 19:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuArticleCommentController {

    @Autowired
    JianshuArticleCommentService jianshuArticleCommentService;

    @PostMapping("/article/comment")
    public Integer addComment(@RequestBody  JianshuArticleComment record){
        record.setCreateTime(new Date());
        record.setStatus(1);
        record.setLikeCount(0);
        return jianshuArticleCommentService.insertSelective(record);
    }

    @GetMapping("/article/comment")
    public PageInfo<Map<String, Object>> getCommentWithUser(Integer pageNum,Integer articleId,@RequestParam(required=false,defaultValue = "-1") Integer userId){
        List<Map<String, Object>> list = jianshuArticleCommentService.selectCommentWithUser(pageNum,articleId,userId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @GetMapping("/article/reply")
    public PageInfo<Map<String, Object>> getReplyWithUser(Integer pageNum,Integer articleId,Integer parentId,@RequestParam(required=false,defaultValue = "-1") Integer userId){
        List<Map<String, Object>> list = jianshuArticleCommentService.selectReplyWithUser(pageNum,articleId,parentId,userId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
