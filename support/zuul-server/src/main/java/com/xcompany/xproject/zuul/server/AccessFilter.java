package com.xcompany.xproject.zuul.server;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);
	
	
	//http://blog.didispace.com/springcloud5/
	@Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return Integer.MIN_VALUE;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
    	LOGGER.info("==============================================");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null) {
//        	LOGGER.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        LOGGER.info("access token ok");
        String authString = request.getHeader("Authorization");
        LOGGER.info("authString: {}", authString);
        LOGGER.info("==============================================");
        return null;
    }

}
