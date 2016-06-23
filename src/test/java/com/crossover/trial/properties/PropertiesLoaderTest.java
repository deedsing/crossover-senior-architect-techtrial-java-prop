package com.crossover.trial.properties;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertiesLoaderTest {

	/**
	 * classpath:resources/aws.properties classpath:resources/config.json classpath:resources/jdbc.properties 
	 */
	private Logger log = Logger.getLogger(PropertiesLoaderTest.class);
	private final String output= "JunitTestFileOutput";
	private final String config1= "classpath:resources/aws.properties";
	private final String config2= "classpath:resources/config.json";
	private final String config3= "classpath:resources/jdbc.properties";
	
	
	@Test
	public void testClassPathPropertiesLoadingConfigValid() throws URISyntaxException, IOException {
	      log.info("Starting the loading of configuration information");
	        // process command line arguments into URIs
	        File outputFile = new File(output);
	        if (outputFile.exists()) {
	            outputFile.delete();
	        }
	        if(outputFile!=null)
	        log.info("The output file where all the loaded system properties will be written is : "+ outputFile.getAbsolutePath());

	        List<String> propertySourceUris = new ArrayList<String>();
	        propertySourceUris.add(config1);
	        propertySourceUris.add(config2);
	        propertySourceUris.add(config3);
	        log.info("Property source URI's where system will try to load the properties: "+propertySourceUris);
	        // invoke the property parser and print out properties alphabetically
	        AppPropertiesManager m = new TrialAppPropertiesManager();
	        AppProperties props = m.loadProps(propertySourceUris);
	     
	       //direct to file
	         m.printProperties(props, new PrintStream(new FileOutputStream(outputFile)));
	        //direct to sysout
	       // m.printProperties(props, System.out);
	        
	        log.info("Available properties: "+props.getKnownProperties());
	        log.info("Missing properties: "+props.getMissingProperties());
	        log.info("Is configuration valid ? "+props.isValid());
	        
	        equals(props.isValid());
	        
	    }
	
	
	@Test
	public void testClassPathPropertiesLoadingConfigInvalid() throws URISyntaxException, IOException {
	      log.info("Invalid configuration test ... ");
	        // process command line arguments into URIs
	        File outputFile = new File(output);
	        if (outputFile.exists()) {
	            outputFile.delete();
	        }
	        if(outputFile!=null)
	        log.info("The output file where all the loaded system properties will be written is : "+ outputFile.getAbsolutePath());

	        List<String> propertySourceUris = new ArrayList<String>();
	        propertySourceUris.add(config1);
	        propertySourceUris.add(config2);
	        log.info("Property source URI's where system will try to load the properties: "+propertySourceUris);
	        // invoke the property parser and print out properties alphabetically
	        AppPropertiesManager m = new TrialAppPropertiesManager();
	        AppProperties props = m.loadProps(propertySourceUris);
	     
	       //direct to file
	         m.printProperties(props, new PrintStream(new FileOutputStream(outputFile)));
	        //direct to sysout
	       // m.printProperties(props, System.out);
	        
	        log.info("Available properties: "+props.getKnownProperties());
	        log.info("Missing properties: "+props.getMissingProperties());
	        log.info("Is configuration valid ? "+props.isValid());
	        
	        equals(!props.isValid());
	        
	    }
	
	
	
}
