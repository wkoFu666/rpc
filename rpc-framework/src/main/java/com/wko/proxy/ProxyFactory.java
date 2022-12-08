package com.wko.proxy;

import com.wko.common.Invocation;
import com.wko.common.URL;
import com.wko.loadbalance.LoadBalance;
import com.wko.protocol.HttpClient;
import com.wko.register.MapRemoteRegister;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * ClassName: proxyFactory
 * Package: com.wko.proxy
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 22:07
 * @Version 1.0
 */
public class ProxyFactory {


    public static <T> T getProxy(Class interfaceClass) {

        //动态代理 读取用户配置
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                (proxy, method, args) -> {

                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                            method.getParameterTypes(), args);


                    //服务mock
                    String mock=System.getProperty("mock");
                    if(mock!=null && mock.startsWith("return:")){
                        String result= mock.replace("return:","");
                        return result;
                    }




                    HttpClient httpClient = new HttpClient();
                    //服务发现
                    List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());

                    //服务重试
//                        int countMax;
//                        while(countMax>0)

                    //负载均衡
                    URL random = LoadBalance.random(urls);
                    String result = null;
                    //服务调用
                    try {
                        result = httpClient.send(random.getHostname(), random.getPort(), invocation);
                    } catch (Exception e) {
                        //容错逻辑
                        return "服务异常！";
                    }

                    return result;
                });
        return (T) proxyInstance;
    }
}
