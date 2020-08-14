package com.externalrestsrping.springbootextrernalapi.service;

public interface ParsingService {
	/**
	 * Service to parse Json response and convert it to model class
	 */
		Object parse(String url);
}
