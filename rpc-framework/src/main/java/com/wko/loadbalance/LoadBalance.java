package com.wko.loadbalance;

import com.wko.common.URL;

import java.util.List;
import java.util.Random;

/**
 * ClassName: LoadBalance
 * Package: com.wko.loadbalance
 * Description:负载均衡策略实现
 *
 * @Author wko
 * @Create 2022/12/7 22:38
 * @Version 1.0
 */
public class LoadBalance {

    /**
     * 简单随机策略实现
     *
     * @param urls 主机地址列表
     * @return 主机地址信心
     */
    public static URL random(List<URL> urls) {
        Random random = new Random();
//        if(CollectionUtils.isEmpty(urls))
        int nextInt = random.nextInt(urls.size());
        return urls.get(nextInt);
    }
}
