package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

// Lombok을 사용하기 때문에 Logger 객체를 생성하지 않아도 됨.
@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    // 어떤 동작을 하는지 작성하는 함수
    @Override
    public Object run() throws ZuulException {
        log.info("**************** printing logs: ");

        // Cient가 요청한 request 정보
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("**************** " + request.getRequestURI());

        return null;
    }

    // 사전 필터인지 후 필터인지 체크하는 함수
    @Override
    public String filterType() {
        return "pre";
    }

    // 필터가 여러개 일 경우 순서를 정하는 함수
    @Override
    public int filterOrder() {
        return 1;
    }

    // 필터로 사용할 것인지 아닌지 정하는 함수
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
