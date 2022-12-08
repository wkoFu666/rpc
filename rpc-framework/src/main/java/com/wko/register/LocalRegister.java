package com.wko.register;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LocalRegister
 * Package: com.wko.register
 * Description:本地注册
 *
 * @Author wko
 * @Create 2022/12/7 21:26
 * @Version 1.0
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName,String version, Class implClass) {
        map.put(interfaceName+version, implClass);
    }

    public static Class get(String interfaceName,String version) {
        return map.get(interfaceName+version);
    }
}
