package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
// @RequestMapping(URI) 자동으로 uri 값을찾아오는게 value
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
	public String value(); 
}
