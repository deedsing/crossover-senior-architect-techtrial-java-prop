package com.crossover.trial.properties.loaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.crossover.trial.properties.exceptions.UnsupportedFileFormatException;
import com.crossover.trial.properties.model.AcceptedFileFormats;
import com.crossover.trial.properties.utils.Parser;;

/**
 * All the resources of web origin are loaded using UrlLoader.This class implements Loader and is instantiated using LoaderFactory.
 * @author deedsing
 *
 */
public class UrlLoader implements Loader{

	private Logger log = Logger.getLogger(UrlLoader.class);
	
	
	@Override
	public Map<String, Object> getProperties(String filename) {
		
		return getPropertiesFromURL(filename);
	}
	
	
	private  Map<String,Object> getPropertiesFromURL(String path) {

		Map<String,Object> map = new HashMap<String,Object>();
		URL url;
		String contents="";
		try {
			url = new URL(path);
			URLConnection con = url.openConnection();
			
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			contents = IOUtils.toString(in, encoding);
			if(!contents.isEmpty()){
			if(path.endsWith(AcceptedFileFormats.PROPERTIES.getFileFormat()))
				map = Parser.parseProperties(contents);
				else if(path.endsWith(AcceptedFileFormats.JSON.getFileFormat()))
					map = Parser.parseJson(contents);
				
			}else
				log.debug("Nothing to process as file is empty");
		} catch (MalformedURLException e) {
			log.error("URL not right , please check the url again "+path);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			log.error("Not able to find the file at destination path :"+path);
			e.printStackTrace();
		}catch (IOException e) {
			log.error(e+" :"+path);
			log.error(e);
		} catch(Exception e){
			log.error(e+" :"+path);
			e.printStackTrace();
		}
		
		return map;

	}
	
	
}
