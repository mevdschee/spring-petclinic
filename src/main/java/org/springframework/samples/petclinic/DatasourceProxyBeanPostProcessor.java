package org.springframework.samples.petclinic;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.tqdev.metrics.core.MetricRegistry;
import com.tqdev.metrics.jdbc.InstrumentedDataSource;

//@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		if (bean instanceof DataSource) {
			DataSource dataSourceBean = (DataSource) bean;
			InstrumentedDataSource instrumentedDatasource = new InstrumentedDataSource(dataSourceBean,
					MetricRegistry.getInstance());
			instrumentedDatasource.setMetricsEnabled(true);
			return instrumentedDatasource;
		}
		return bean;
	}
}