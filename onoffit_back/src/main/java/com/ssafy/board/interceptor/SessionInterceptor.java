package com.ssafy.board.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.board.util.JwtUtil;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtil jwtutil;
	// 컨트롤러 호출 전에 실행되어야 한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		String token = request.getHeader("access-token");
		
		if(token != null) {
			try{
				jwtutil.valid(token);
				return true; 
			}catch(Exception e) {
				response.sendError(401);
				System.out.println("asdasd");
				return false;
			}
		}
		response.sendError(401);
		return false;
	}
	
}

