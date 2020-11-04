package io.github.lonelyWalker78.gateway.router;

import java.util.List;

/**
 * Description: 网关路由器 顺序列表策略
 * Created on 2020/11/4
 *
 * @author lincj
 */
public class OrderRouter implements HttpEndpointRouter {

    private int index = 0;

    @Override
    public String route(List<String> endpoints) {
        if (endpoints.isEmpty()) {
            return null;
        }
        String endpoint = endpoints.get(index);
        if (index == endpoints.size()-1) {
            index = 0;
        } else {
            index++;
        }
        return endpoint;
    }
}
