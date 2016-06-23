package com.crossover.trial.properties.loaders;

import java.util.Map;

/**
 * public interface for defining the contract between different implementations that load URI resources.
 * @author deedsing
 *
 */
public interface Loader {


	public Map<String,Object> getProperties(String filename) ;
	
}
