package com.xucg.util.http;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    /**
     * post发送文件
     */
    public static String postWeixinMaterial(String url, String imageLocalPath, Map<String, String> paramMap) {

        File file = new File(imageLocalPath);
        PostMethod method = new PostMethod(url);
        HttpClient client = new HttpClient();

        try {
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");        // 中文乱码

//            NameValuePair[] pairs = new NameValuePair[paramMap.size()];
//            int i = 0;
            for (Map.Entry entry : paramMap.entrySet()) {
                method.getParams().setParameter(entry.getKey().toString(), entry.getValue().toString());
            }

            // 设置文件
            Part[] parts = {new FilePart("media", file)};
            method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));

            // 设置参数
//            NameValuePair[] pairs = new NameValuePair[paramMap.size()];
//            int i = 0;
//            for (Map.Entry entry : paramMap.entrySet()) {
//                pairs[i++] = new NameValuePair(entry.getKey().toString(), entry.getValue().toString());
//            }
//            method.setRequestBody(pairs);


            return post(method);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return null;
    }

    public static String post(String url, Map<String, String> paramMap) {
        PostMethod method = buildPostMethod(url, paramMap);
        return post(method);
    }

    public static String post(String url, String data) {
        PostMethod method = buildPostMethod(url, data);
        return post(method);
    }

    public static String post(PostMethod method) {
        HttpClient client = new HttpClient();
        StringBuilder sb = null;
        BufferedReader reader = null;
        try {
            int code = client.executeMethod(method);
            if (code != 200) {
                return null;
            }

            InputStream in = method.getResponseBodyAsStream();
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String result;
            sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static PostMethod buildPostMethod(String url, Map<String, String> paramMap) {
        PostMethod method = new PostMethod(url);
        NameValuePair[] pairs = new NameValuePair[paramMap.size()];
        int i = 0;
        for (Map.Entry entry : paramMap.entrySet()) {
            pairs[i++] = new NameValuePair(entry.getKey().toString(), entry.getValue().toString());
        }
        method.setRequestBody(pairs);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");        // 中文乱码
        return method;
    }

    private static PostMethod buildPostMethod(String url, String data) {
        PostMethod method = new PostMethod(url);
        RequestEntity entity = null;
        try {
            entity = new StringRequestEntity(data, null, "UTF-8");
            method.setRequestEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return method;
    }

    public static String get(String url) {
        return get(url, new HashMap<String, String>());
    }

    public static String get(String url, Map<String, String> map) {

        HttpClient client = new HttpClient();
        HttpMethod method = buildGetMethod(url, map);
        StringBuilder sb = null;
        BufferedReader reader = null;
        try {
            int code = client.executeMethod(method);
            if (code != 200) {
                return null;
            }

            InputStream in = method.getResponseBodyAsStream();
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String result;
            sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static GetMethod buildGetMethod(String url, Map<String, String> paramMap) {
        GetMethod method = new GetMethod(url);
        NameValuePair[] pairs = new NameValuePair[paramMap.size()];
        int i = 0;
        for (Map.Entry entry : paramMap.entrySet()) {
            pairs[i++] = new NameValuePair(entry.getKey().toString(), entry.getValue().toString());
        }

        method.setQueryString(pairs);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");        // 中文乱码
        return method;
    }

    public static void main(String[] args) {
        String a = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=fer8Jm4mLCrKrR-L1DXsTw5ZxTn3v8KmM7TvGpU21kQjjA0vczMYPFGEkUxaiDKxpbZsrznk6ZXHNpCl82xug7fNsHHLCLPJzvNuKrtuoyW3Zyxat7YcZSxCQdsTMjSlUMOaAFDBTV&type=image";
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=fer8Jm4mLCrKrR-L1DXsTw5ZxTn3v8KmM7TvGpU21kQjjA0vczMYPFGEkUxaiDKxpbZsrznk6ZXHNpCl82xug7fNsHHLCLPJzvNuKrtuoyW3Zyxat7YcZSxCQdsTMjSlUMOaAFDBTV&";
        String im = "/Users/emp/Downloads/a.png";
        Map<String, String> param = new HashMap<>();
        param.put("id", "media");
        param.put("type", "image");

        System.out.println(postWeixinMaterial(url, im, param));
    }

}
