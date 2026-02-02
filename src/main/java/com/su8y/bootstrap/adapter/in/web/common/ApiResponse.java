package com.su8y.bootstrap.adapter.in.web.common;

import java.util.Map;

public class ApiResponse<T> {
	private final String status;
	private final T content;

	private ApiResponse(String status, T content) {
		this.status = status;
		this.content = content;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>("success", data);
	}

	public static <T> ApiResponse<Map<String, T>> success(String key, T data) {
		return new ApiResponse<>("success", Map.of(key, data));
	}


	public String getStatus() {
		return status;
	}

	public T getContent() {
		return content;
	}
}
