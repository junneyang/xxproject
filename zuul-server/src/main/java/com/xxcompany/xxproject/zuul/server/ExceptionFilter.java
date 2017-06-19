package com.xxcompany.xxproject.zuul.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class ExceptionFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionFilter.class);
	protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";
	
	@Override
	public Object run() {
		//http://blog.didispace.com/spring-cloud-source-zuul/
		//SendErrorFilter
		// TODO Auto-generated method stub
//		//return null;
//		RequestContext ctx = RequestContext.getCurrentContext();
////        Throwable throwable = ctx.getThrowable();
////        LOGGER.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
////        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
////        ctx.set("error.exception", throwable.getCause());
//		Object e = ctx.get("error.exception");
//		if (e != null && e instanceof ZuulException) {
//			ZuulException zuulException = (ZuulException)e;
//			LOGGER.error("Zuul failure detected: " + zuulException.getMessage(), zuulException);
//			ctx.remove("error.status_code");
//	        //ctx.setSendZuulResponse(false);
//	        ctx.setResponseBody("AAAAAAAAAAAAAAA");
//	        ctx.getResponse().setContentType("application/json");
//	        ctx.setResponseStatusCode(500);
//		}
//        return null;
        
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            //Object e = ctx.get("error.exception");
            Throwable throwable = ctx.getThrowable();
            //HttpServletRequest request = ctx.getRequest();
            // && e instanceof ZuulException
            //if (e != null && e instanceof Exception) {
            if (throwable != null) {
                //ZuulException zuulException = (ZuulException)e;
                //LOGGER.error("Zuul failure detected: " + zuulException.getMessage(), zuulException);
            	//LOGGER.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
            	//Exception exception = (Exception) e;
            	Exception exception = (Exception) throwable.getCause();
            	LOGGER.error("Zuul failure detected: " + exception.getMessage());
            	
                // Remove error code to prevent further error handling in follow up filters
                ctx.remove("error.status_code");

                // Populate context with new response values
                ctx.setResponseBody("Overriding Zuul Exception Body");
                ctx.getResponse().setContentType("application/json");
                ctx.setResponseStatusCode(500); //Can set any error code as excepted
                ctx.setSendZuulResponse(false);
            }
        }
        catch (Exception ex) {
            LOGGER.error("Exception filtering in custom error filter", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		//return false;
		//return RequestContext.getCurrentContext().containsKey("error.status_code");
		//return true;
		RequestContext ctx = RequestContext.getCurrentContext();
		//return ctx.containsKey("error.status_code") && !ctx.getBoolean(SEND_ERROR_FILTER_RAN,false);
		return ctx.getThrowable() != null
				&& !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		//return -1;
		return Integer.MIN_VALUE;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		//return null;
		return "error";
	}
	
}
