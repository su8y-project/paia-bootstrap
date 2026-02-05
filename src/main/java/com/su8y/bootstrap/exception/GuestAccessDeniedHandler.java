package com.su8y.bootstrap.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GuestAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		Map<String, Object> body = new HashMap<>();

		if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_GUEST"))) {
			body.put("code", "NEED_ONBOARDING");
			body.put("message", "Guest user cannot access this resource.");
			body.put("redirectUrl", "/api/v1/auth/status");
		} else {
			body.put("code", "ACCESS_DENIED");
			body.put("message", "Access denied.");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		response.getWriter().write(objectMapper.writeValueAsString(body));

	}
}
