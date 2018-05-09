package com.hk.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

@Configuration
public class HystrixFallbackConfiguration {

	@Bean
	public FallbackProvider zuulFallbackProvider() {
		return new FallbackProvider() {

			public String getRoute() {
				// Might be confusing: it's the serviceId property and not the route
				return "first-service";
			}

			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				return new ClientHttpResponse() {

					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						return headers;
					}

					public InputStream getBody() throws IOException {
						return new ByteArrayInputStream("{Sorry Service is Down first-service}".getBytes());
					}

					public String getStatusText() throws IOException {
						return HttpStatus.OK.toString();
					}

					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.OK;
					}

					public int getRawStatusCode() throws IOException {
						return HttpStatus.OK.value();
					}

					public void close() {
						// TODO Auto-generated method stub

					}
				};
			}

		};
	}

	@Bean
	public FallbackProvider securityFallback() {
		return new FallbackProvider() {

			public String getRoute() {
				// Might be confusing: it's the serviceId property and not the route
				return "security-service";
			}

			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				return new ClientHttpResponse() {

					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						return headers;
					}

					public InputStream getBody() throws IOException {
						return new ByteArrayInputStream("{Sorry Service is Down second-service}".getBytes());
					}

					public String getStatusText() throws IOException {
						return HttpStatus.OK.toString();
					}

					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.OK;
					}

					public int getRawStatusCode() throws IOException {
						return HttpStatus.OK.value();
					}

					public void close() {
						// TODO Auto-generated method stub

					}
				};
			}

		};
	}

}