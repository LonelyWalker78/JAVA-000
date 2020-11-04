package io.github.lonelyWalker78.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * Description: 网关路由器 随机策略
 * Created on 2020/11/4
 *
 * @author lincj
 */
public class RandomRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        Random random = new Random();
        int index = random.nextInt(endpoints.size());
        return endpoints.get(index);
    }
}
