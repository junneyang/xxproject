package com.xcompany.xproject.zuul.server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.filter.GenericFilterBean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableResourceServer
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SpringApplication.run(Application.class, args);
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LOGGER.debug(beanName);
            }
        };
    }
	
//	@Bean
//	public AccessFilter accessFilter() {
//		return new AccessFilter();
//	}
	
//	@Bean
//	public ExceptionFilter exceptionFilter() {
//		return new ExceptionFilter();
//	}
	
//	@Bean
//	public RequestInterceptor requestTokenBearerInterceptor() {
//	    return new RequestInterceptor() {
//			@Override
//			public void apply(feign.RequestTemplate template) {
//				// TODO Auto-generated method stub
//				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
//	                    SecurityContextHolder.getContext().getAuthentication().getDetails();
//
//				template.header("Authorization", "bearer " + details.getTokenValue());
//			}
//
//	    };
//	}
	
	@Bean
    public UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(
            TraceRestTemplateInterceptor traceRestTemplateInterceptor) {
        return restTemplate -> {
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(
                    restTemplate.getInterceptors());
            interceptors.add(traceRestTemplateInterceptor);
            restTemplate.setInterceptors(interceptors);
        };
    }
	
	static class CustomHttpServletResponseSpanInjector implements
	SpanInjector<HttpServletResponse> {

		@Override
		public void inject(Span span, HttpServletResponse carrier) {
			/*carrier.addHeader(Span.TRACE_ID_NAME, span.traceIdString());
			carrier.addHeader(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));*/
			carrier.addHeader("X-RequestId", span.traceIdString());
		}
	}

	static class HttpResponseInjectingTraceFilter extends GenericFilterBean {

		private final Tracer tracer;
		private final SpanInjector<HttpServletResponse> spanInjector;

		public HttpResponseInjectingTraceFilter(Tracer tracer,
				SpanInjector<HttpServletResponse> spanInjector) {
			this.tracer = tracer;
			this.spanInjector = spanInjector;
		}

		@Override
		public void doFilter(ServletRequest request,
				ServletResponse servletResponse, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			Span currentSpan = this.tracer.getCurrentSpan();
			this.spanInjector.inject(currentSpan, response);
			filterChain.doFilter(request, response);
		}
	}
	
	@Bean
	SpanInjector<HttpServletResponse> customHttpServletResponseSpanInjector() {
		return new CustomHttpServletResponseSpanInjector();
	}

	@Bean
	HttpResponseInjectingTraceFilter responseInjectingTraceFilter(Tracer tracer) {
		return new HttpResponseInjectingTraceFilter(tracer, customHttpServletResponseSpanInjector());
	}

}
