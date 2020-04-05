package com.choza.pequenines.vscovid.security;

public class SecurityConstant {

	public static final long EXPIRATION_TIME = 1 * 60 * 60 * 24 * 1000; // 1 dia
	public static final long EXPIRATION_TEMPORARY_TOKEN_TIME = 1 * 60 * 60 * 24 * 1000; // 1 dia
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String SECRET = "covid19";
}
