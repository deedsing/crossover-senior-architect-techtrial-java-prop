package com.crossover.trial.properties.loaders;

import com.crossover.trial.properties.model.AcceptedURI;

/**
 * A factory class which will instantiate specific Loader implementation based on the URI provided.
 * @author deedsing
 *
 */
public class LoaderFactory  {

	 public static Loader getLoader(String uriPath){
	      if(uriPath == null){
	         return null;
	      }		
	      if(uriPath.startsWith(AcceptedURI.URI_CLASSPATH.getUri())){
	         return new ClasspathLoader();
	         
	      } else if(uriPath.startsWith(AcceptedURI.URI_FILE.getUri())){
	         return new FileLoader();
	         
	      } else if(uriPath.startsWith(AcceptedURI.URI_HTTP.getUri())){
	         return new UrlLoader();
	      }
	      
	      return null;
	   }
}
