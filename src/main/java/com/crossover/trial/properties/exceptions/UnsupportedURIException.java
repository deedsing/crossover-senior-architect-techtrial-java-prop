package com.crossover.trial.properties.exceptions;


/**
 * UnsupportedURIException is raised if the URI is not supported.
 * @author deedsing
 *
 */
public class UnsupportedURIException extends Exception{


	private static final long serialVersionUID = -2396340116409132319L;
	private static final String exception ="The URI is not supported ";
	
	
	public UnsupportedURIException(String uri){
		super(exception + ": "+ uri);
	}
	
	public UnsupportedURIException(){
		super(exception);
	}

}
