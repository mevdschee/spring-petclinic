/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tqdev.metrics.core.MetricRegistry;
import com.tqdev.metrics.influxdb.InfluxDbHttpReporter;
import com.tqdev.metrics.jvm.SystemMonitor;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
@ComponentScan({ "org.springframework.samples.petclinic", "com.tqdev.metrics.spring.loaders" })
public class PetClinicApplication {

	public static void main(String[] args) throws Exception {
		MetricRegistry registry = MetricRegistry.getInstance();
		new SystemMonitor(registry);
		new InfluxDbHttpReporter("http://localhost:8086/write?db=collectd", "localhost", false, registry).run(10);
		SpringApplication.run(PetClinicApplication.class, args);
	}

}
