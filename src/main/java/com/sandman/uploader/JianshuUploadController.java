package com.sandman.uploader;

//import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

//import org.junit.Assert;
import net.sf.json.JSONObject;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2019.02.14 15:54
 * @UpdateUser: yuwentao
 * @UpdateDate: 2019.02.14 15:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
public class JianshuUploadController {
    @GetMapping("getUploadToken")
    public Map<String, String> uploader(){

//        String accessKey = "mV7bS6qmzOdY3wKdD0jR-ph2OK40C2GPiEZQPWUw";
//        String secretKey = "mrR8w7n_PyP44Evo_fQiHElD750ii8Dg_-suQN9W";
//        String bucket = "jianshu-article-img";
//        Long time = System.currentTimeMillis();
//        String key = time.toString();
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket, key);
//
//        JSONObject res = new JSONObject();
//        res.put("token",upToken);
//        res.put("key",key);
//        return res;

        String accessId = "LTAIWFHNawjrByo6"; // 请填写您的AccessKeyId。
        String accessKey = "zHYkAgedHnXITxQs76vOpWEtF2jljb"; // 请填写您的AccessKeySecret。
        String endpoint = "oss-cn-qingdao.aliyuncs.com"; // 请填写您的 endpoint。
        String bucket = "jianshu-pic"; // 请填写您的 bucketname 。
        String host = "http://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = "http://10.195.174.120.:8080";
        String dir = "images/"; // 用户上传文件时指定的前缀。

        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

//            JSONObject ja1 = JSONObject.fromObject(respMap);
            // System.out.println(ja1.toString());
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
//            response(request, response, ja1.toString());
            return  respMap;

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
            return  null;

        }
    }
}
