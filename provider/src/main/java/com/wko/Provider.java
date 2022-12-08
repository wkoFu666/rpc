package com.wko;

import com.wko.common.URL;
import com.wko.protocol.HttpServer;
import com.wko.register.LocalRegister;
import com.wko.register.MapRemoteRegister;

/**
 * ClassName: Provider
 * Package: com.wko
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 20:41
 * @Version 1.0
 */
public class Provider {

    public static void main(String[] args) {
        //进行本地注册
        LocalRegister.register(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(),"2.0", HelloService2Impl.class);

        //向注册中心注册本机地址
        URL url = new URL("localhost", 8080);
        MapRemoteRegister.register(HelloService.class.getName(),url);

        //TODO 使用Tomcat接收请求
        HttpServer httpServer=new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());

    }
}
