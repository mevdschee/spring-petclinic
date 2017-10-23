package org.springframework.samples.petclinic;

import javax.servlet.annotation.WebFilter;

import org.springframework.context.annotation.Configuration;

import com.tqdev.metrics.core.MetricRegistry;
import com.tqdev.metrics.spring.MeasureRequestPathFilter;

@Configuration
@WebFilter
public class MeasureRequestPathFilterLoader extends MeasureRequestPathFilter {

	public MeasureRequestPathFilterLoader() {
		super(MetricRegistry.getInstance(), "json|xml|html|csv");
	}

}
