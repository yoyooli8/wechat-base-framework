 package com.xa3ti.business.util;

import com.xa3ti.business.entity.Constant;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信工具
 * @author Haner
 *
 */
public class WeixinUtil {
    /**
     * 加密/校验流程如下：     
     * @return
     */
    public static boolean checkWeixinReques(String signature, String timestamp, String nonce){
        String[] arr = {Constant.TOKEN,timestamp,nonce};
//        * 1. 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
//        * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String tempStr = "";
        for(String str:arr){
            tempStr += str;
        }
        tempStr = sha1(tempStr);
//        * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return tempStr != null?tempStr.equals(signature):false;
    }
    
    /**
     * sha加密
     * @param str
     * @return
     */
    public static String sha1(String str){
        String key = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            key = new BigInteger(1,md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }




   
}

