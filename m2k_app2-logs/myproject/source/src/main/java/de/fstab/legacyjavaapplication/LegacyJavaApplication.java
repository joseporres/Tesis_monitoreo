package de.fstab.legacyjavaapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
@EnableAutoConfiguration
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class LegacyJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegacyJavaApplication.class, args);
	}
}
