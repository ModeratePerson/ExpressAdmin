package com.express.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.util.HashMap;

public class SMSSender {
    public static boolean send(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "你的accessKeyId", "你的secret");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "你的标签名");
        request.putQueryParameter("TemplateCode", "你的模板名");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.getData());
        String json = response.getData();
        Gson g = new Gson();
        HashMap result = g.fromJson(json, HashMap.class);
        if("OK".equals(result.get("Message"))) {
            return true;
        }
        return false;
    }
}
