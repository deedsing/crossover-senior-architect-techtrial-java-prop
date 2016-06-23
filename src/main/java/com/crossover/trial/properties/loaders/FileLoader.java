package com.crossover.trial.properties.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.crossover.trial.properties.exceptions.UnsupportedFileFormatException;
import com.crossover.trial.properties.model.AcceptedFileFormats;
import com.crossover.trial.properties.utils.Parser;

/**
 * All the resources of absolute file path origin are loaded using FileLoader.This class implements Loader and is instantiated using LoaderFactory.
 * @author deedsing
 *
 */
public class FileLoader implements Loader{

	private  Logger log =Logger.getLogger(FileLoader.class);
	
	
	@Override
	public Map<String, Object> getProperties(String filename) {
		return getPropertiesFromFile(filename);
	}
	
	
	
	
	private Map<String,Object> getPropertiesFromFile(String filename)  {

		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String contents = readFile(filename);
			if(contents!=null &&!contents.isEmpty()){
			if(filename.endsWith(AcceptedFileFormats.PROPERTIES.getFileFormat()))
				map = Parser.parseProperties(contents);
				else if(filename.endsWith(AcceptedFileFormats.JSON.getFileFormat()))
					map = Parser.parseJson(contents);
				else
					throw new UnsupportedFileFormatException();
			}else
				log.debug("Nothing to process as file is empty or null");
		} catch (FileNotFoundException e) {
			log.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.toString());
			e.printStackTrace();
		} catch (UnsupportedFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return map;

	}
	

	private String readFile( String file ) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while( ( line = reader.readLine() ) != null ) {
	            stringBuilder.append( line );
	            stringBuilder.append( ls );
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}


	
	
	
	}
