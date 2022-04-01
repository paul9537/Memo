package com.paul9537.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	// 요청이 들어 올때
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws IOException {
		
		// 로그인 여부 확인
		HttpSession session = request.getSession();
		
		// /post/list_view
		String uri = request.getRequestURI();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 로그인 안된상태
		if(userId == null) {
			// 리스트 화면 /post/list_view
			// 글쓰기 화면 /post/create_view
			if(uri.startsWith("/post")) {
				// 로그인 화면으로 이동
				response.sendRedirect("/user/signin_view");
				return false;
			}
			
		} else { // 로그인이 된 상태
			// 로그인화면 /user/signin_view
			// 회원가입화면 /user/signup_view
			if(uri.startsWith("/user")) {
				// 리스트 화면으로 이동
				response.sendRedirect("/post/list_view");
				return false;
			}
			
		}
		
		return true;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object handler,
			ModelAndView modelAndView) {
		
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex) {
		
	}
}
