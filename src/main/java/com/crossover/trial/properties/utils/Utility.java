package com.crossover.trial.properties.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;

import com.crossover.trial.properties.loaders.ClasspathLoader;
import com.crossover.trial.properties.loaders.FileLoader;
import com.crossover.trial.properties.loaders.Loader;
import com.crossover.trial.properties.loaders.UrlLoader;
import com.crossover.trial.properties.model.AcceptedURI;

/**
 * This is a helper class. It has methods defined for a very specific tasks.
 * @author deedsing
 *
 */
public class Utility {
	
	
	public static String getPath(String s, String uri) {
		if(AcceptedURI.URI_HTTP.getUri().equals(uri))
			return s;
		else 
			return s.substring(uri.length());
		
	}
	
	public static String neutralizeKey(String key){
		key=key.toLowerCase();
		key=key.replace('.', '_');
		
		return key;
	}
	
	public static String neutralizeRegion(String value){
		value=value.toUpperCase();
		value=value.replace('-', '_');
		value=value.replace('.', '_');
		
		return value;
	}


	public static String getURIType(String name) {
		
		
		if(FileLoader.class.getName().equals(name))
			return AcceptedURI.URI_FILE.getUri();
		else if(UrlLoader.class.getName().equals(name))
			return AcceptedURI.URI_HTTP.getUri();
		else if (ClasspathLoader.class.getName().equals(name))
			return AcceptedURI.URI_CLASSPATH.getUri();
		
		return null;
	}


	
}
