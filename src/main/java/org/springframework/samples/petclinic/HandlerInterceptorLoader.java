package org.springframework.samples.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tqdev.metrics.core.MetricRegistry;
import com.tqdev.metrics.spring.MvcDurationInterceptor;

@Configuration
public class HandlerInterceptorLoader extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MvcDurationInterceptor(MetricRegistry.getInstance()));
	}
}