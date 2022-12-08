package com.wko;

/**
 * ClassName: HelloServiceImpl2
 * Package: com.wko
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 21:36
 * @Version 1.0
 */
public class HelloService2Impl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "version 2.0 :"+name;
    }
}
