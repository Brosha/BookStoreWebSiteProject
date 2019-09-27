package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final String[] LoginRequieredUrls = {"/view_profile", "/edit_profile",
			"/update_profile","/write_review","/checkout","/place_order", "/view_orders","/show_order_detail" } ;
    public CustomerLoginFilter() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		boolean loggedIn = session !=null && session.getAttribute("loggedCustomer") !=null;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if(path.startsWith("/admin/")){
			chain.doFilter(request, response);
			return;
		}
		if(!loggedIn && isLoginRequiredUrl(path)) {
			String redirectURL= httpRequest.getRequestURL().toString();
			String queryString= httpRequest.getQueryString();
			if(queryString!=null)
				redirectURL+="?"+queryString;
			
			session.setAttribute("redirectURL", redirectURL);
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/login");
			dispatcher.forward(httpRequest, response);
		}
		else {
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	private boolean isLoginRequiredUrl(String path) {
		for (String string : LoginRequieredUrls) {
			if(path.startsWith(string))
				return true;
		}
		return false;
	}

}
