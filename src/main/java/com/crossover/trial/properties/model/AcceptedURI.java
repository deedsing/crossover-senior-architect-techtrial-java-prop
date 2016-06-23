package com.crossover.trial.properties.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The accepted URI's by the system.
 * @author deedsing
 *
 */
public enum AcceptedURI {

	
	URI_CLASSPATH("classpath:resources/"),
	 URI_FILE("file://"),
    URI_HTTP("http");
	 
	    private String uri;
	    public String getUri(){return uri;}
	    public void setUri(String uri){this.uri = uri;}
	    private AcceptedURI(String uri){this.uri = uri;}
	    
	    public static final List<String> list = Collections.unmodifiableList(initialize());
		private static List<String> initialize() {
			List<String> list = new ArrayList<String>();
	        for (AcceptedURI uri : AcceptedURI.values()) {
	            list.add(uri.getUri());
	        }
			return list;
		}
}
