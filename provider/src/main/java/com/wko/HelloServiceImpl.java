package com.wko;

/**
 * ClassName: HelloServiceImpl
 * Package: com.wko
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 20:31
 * @Version 1.0
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "Hello: "+name;
    }
}
