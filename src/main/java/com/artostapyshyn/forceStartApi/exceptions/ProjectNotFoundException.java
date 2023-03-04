package com.artostapyshyn.forceStartApi.exceptions;

public class ProjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ProjectNotFoundException(String parameter) {
		 super("Couldn't find any projects by - " + parameter);
	}
}
