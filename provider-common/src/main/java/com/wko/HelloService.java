package com.wko;

/**
 * ClassName: HelloService
 * Package: com.wko
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 21:05
 * @Version 1.0
 */
public interface HelloService {

    /**
     * 测试rpc调用的方法
     *
     * @param name 姓名
     * @return str
     */
    public String sayHello(String name);
}
