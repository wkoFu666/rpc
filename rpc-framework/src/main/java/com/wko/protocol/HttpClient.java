package com.wko.protocol;

import com.wko.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ClassName: HttpClient
 * Package: com.wko.protocol
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 21:47
 * @Version 1.0
 */
public class HttpClient {

    public static final String HTTP = "http";
    public static final String POST = "POST";

    /**
     * 发送http请求
     *
     * @param hostname   主机
     * @param port       端口
     * @param invocation 发送内容
     * @return str
     */
    public String send(String hostname, Integer port, Invocation invocation) {
        //TODO 根据用户的配置进行发送
        //Java方式实现
        try {
            URL url = new URL(HTTP, hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(POST);
            httpURLConnection.setDoOutput(true);

            //配置
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            return IOUtils.toString(inputStream);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
