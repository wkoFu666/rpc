package com.wko;

import com.wko.common.Invocation;
import com.wko.protocol.HttpClient;
import com.wko.proxy.ProxyFactory;

/**
 * ClassName: Consumer
 * Package: com.wko
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 20:34
 * @Version 1.0
 */
public class Consumer {

    public static void main(String[] args) {
        //手动实现 请求发送
        Invocation invocation=new Invocation(HelloService.class.getName(),"sayHello",
                new Class[]{String.class},new Object[]{"wko"});

        HttpClient httpClient=new HttpClient();
        String res = httpClient.send("localhost", 8080, invocation);
        System.out.println(res);

        //动态代理 优化
        HelloService helloService= ProxyFactory.getProxy(HelloService.class);
        String result=helloService.sayHello("wko-proxy");
        System.out.println(result);
    }
}
