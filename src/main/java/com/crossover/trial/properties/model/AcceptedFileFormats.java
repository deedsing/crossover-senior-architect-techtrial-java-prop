package com.crossover.trial.properties.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The accepted file formats by the system.
 * @author deedsing
 *
 */
public enum AcceptedFileFormats {

	 PROPERTIES(".properties"),
	 JSON(".json");
	 
	    private String fileFormat;
	    public String getFileFormat(){return fileFormat;}
	    public void setFileFormat(String uri){this.fileFormat = uri;}
	    private AcceptedFileFormats(String fileFormat){this.fileFormat = fileFormat;}
	    
	    public static final List<String> list = Collections.unmodifiableList(initialize());
		private static List<String> initialize() {
			List<String> list = new ArrayList<String>();
	        for (AcceptedFileFormats formats : AcceptedFileFormats.values()) {
	            list.add(formats.getFileFormat());
	        }
			return list;
		}
		
		
}
