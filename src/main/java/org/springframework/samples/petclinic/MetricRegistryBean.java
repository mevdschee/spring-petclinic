package org.springframework.samples.petclinic;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tqdev.metrics.core.MetricRegistry;

@Component
public class MetricRegistryBean {
	@Bean
	MetricRegistry metricRegistry() {
		return MetricRegistry.getInstance();
	}
}
