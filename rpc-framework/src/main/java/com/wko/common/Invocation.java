package com.wko.common;

import java.io.Serializable;

/**
 * ClassName: Invocation
 * Package: com.wko.common
 * Description: 调用信息的封装类
 *
 * @Author wko
 * @Create 2022/12/7 21:18
 * @Version 1.0
 */
public class Invocation implements Serializable {



    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数类型
     */
    private Class[] parameterTypes;

    /**
     * 方法参数
     */
    private Object[] parameter;

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameter) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameter = parameter;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameter() {
        return parameter;
    }

    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }
}
