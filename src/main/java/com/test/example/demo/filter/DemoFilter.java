package com.test.example.demo.filter;


import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.example.demo.utilities.StringUtils;


/**
 * <<This is the filter for angular and other front end>>
 * @author Munal
 * @version 1.0.0
 * @since , 23 Feb 2018
 */
@Component
public class DemoFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(DemoFilter.class);


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> requestHeaders = new HashMap<String, String>();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = ((HttpServletRequest) req).getHeader(key);
			requestHeaders.put(key, value);
		}
		String originHeaders = requestHeaders.get("origin");
		response.setHeader("Access-Control-Allow-Origin", originHeaders);

		response.setHeader("Access-Control-Allow-Methods",
				"GET, POST, DELETE, PUT, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "86400");
		filterChain.doFilter(req, response);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	boolean didOriginateFrom(List<String> domains, String requestHost) {
		return StringUtils.contains(domains, requestHost);
	}
}

