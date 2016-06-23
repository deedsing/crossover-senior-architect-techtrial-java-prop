package com.crossover.trial.properties.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.crossover.trial.properties.model.AcceptedFileFormats;
import com.crossover.trial.properties.utils.Parser;

/**
 * All the resources of classpath origin are loaded using ClasspathLoader.This
 * class implements Loader and is instantiated using LoaderFactory.
 * 
 * @author deedsing
 *
 */
public class ClasspathLoader implements Loader {

	private Logger log = Logger.getLogger(ClasspathLoader.class);

	@Override
	public Map<String, Object> getProperties(String filename) {
		return getPropertiesFromClasspath(filename);
	}

	private Map<String, Object> getPropertiesFromClasspath(String filename) {

		Map<String, Object> map = new HashMap<String, Object>();
		String contents;
		try {
			contents = getFileContents(filename);
			if (!contents.isEmpty()) {
				if (filename.endsWith(AcceptedFileFormats.PROPERTIES.getFileFormat()))
					map = Parser.parseProperties(contents);
				else if (filename.endsWith(AcceptedFileFormats.JSON.getFileFormat()))
					map = Parser.parseJson(contents);
			} else
				log.debug("Nothing to process as file is empty");
		} catch (IOException e) {
			log.error(e);
		}

		return map;

	}

	private String getFileContents(String fileName) throws IOException {
		String out = "";

		InputStream input = null;

		try {

			input = ClasspathLoader.class.getClassLoader().getResourceAsStream(fileName);
			if (input == null) {
				throw new FileNotFoundException(fileName);
			} else {
                
				out = IOUtils.toString(input, Charset.defaultCharset().toString());

			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return out;

	}
}
