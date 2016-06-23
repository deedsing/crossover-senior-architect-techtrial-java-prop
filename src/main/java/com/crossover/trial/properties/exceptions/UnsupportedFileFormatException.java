package com.crossover.trial.properties.exceptions;

/**<h1>UnsupportedFileFormatException</h1>
 * <p>
 * UnsupportedFileFormatException exception is raised if the file format is not supported by the system.
 * @author deedsing
 *
 */
public class UnsupportedFileFormatException extends Exception{

	private static final long serialVersionUID = 7533716651877671931L;
	private static final String exception ="The file format is not supported, we only support .json or .properties for the time being";
	public UnsupportedFileFormatException(String path){
		super(exception + " The given file is : "+ path);
	}
	
	public UnsupportedFileFormatException(){
		super(exception);
	}
	
}
