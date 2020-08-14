package com.chm.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component //向容器中添加该组件
public class MyErrorAttributes extends DefaultErrorAttributes {
    /**
     * 自定义数据进行响应
     *
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("msg", "您要访问的页面不见了。。。");
        return map;
    }
}
