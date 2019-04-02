package com.sandman.controller;

import com.sandman.pojo.JianshuArticleLike;
import com.sandman.service.JianshuArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.24 22:10
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.24 22:10
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class JianshuArticleLikeController {

    @Autowired
    private JianshuArticleLikeService jianshuArticleLikeService;

    @PostMapping("article/like")
    public int like(@RequestBody  JianshuArticleLike record){
        record.setLikeTime(new Date());
        record.setLikeState("1");
        return jianshuArticleLikeService.insertSelective(record);
    }

    @DeleteMapping("article/like")
    public int unLike(Integer articleId,Integer userId){
        try{
            return jianshuArticleLikeService.deleteByUserIdAndArticleId(articleId,userId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @GetMapping("article/like")
    public List<JianshuArticleLike> selectLiked(Integer articleId){
        return  jianshuArticleLikeService.selectByArticleId(articleId);
    }

}
