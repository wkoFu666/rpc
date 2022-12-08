package com.wko.protocol;

import com.wko.common.Invocation;
import com.wko.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: HttpServerHandler
 * Package: com.wko.protocol
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 21:13
 * @Version 1.0
 */
public class HttpServerHandler {

    /**
     * 处理http请求
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     */
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        //处理请求-->接口、方法、方法参数
        try {
            Invocation invocation =(Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class classImpl = LocalRegister.get(interfaceName,"1.0");
            //通过接口名称 在本地注册列表拿到具体的方法实现
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            //反射执行
            String result = (String) method.invoke(classImpl.newInstance(), invocation.getParameter());
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
