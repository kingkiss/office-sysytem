package com.office.manage.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFilter {

	@Bean
	public RemoteIpFilter remoteIpFilter(){
		return new RemoteIpFilter();
	}
	
	@Bean
	public FilterRegistrationBean testFilterRegistration(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new LoginStatusFilter()); //添加过滤器
		registration.addUrlPatterns("/manage"); //设置过滤路径，/*所有路径
		registration.addInitParameter("name", "value"); //添加默认参数
		registration.setName("LoginStatusFilter"); //设置优先级
		registration.setOrder(1);  //设置有限级
		return registration;
	}
	
	public class LoginStatusFilter implements Filter{

		@Override
		public void destroy() {
		}
		
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			if(session.getAttribute("user_id") == null){
				RequestDispatcher re = req.getRequestDispatcher("login.html");
				re.forward(req, resp);
			}else{
				RequestDispatcher re = req.getRequestDispatcher("manage.html");
				re.forward(req, resp);
			}
			
		}
		
		@Override
		public void init(FilterConfig arg0) throws ServletException {
		}
		
	}
}
