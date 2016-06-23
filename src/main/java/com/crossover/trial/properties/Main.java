package com.crossover.trial.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <h1>Main</h1>
 * <p>
 *This is the main class which will try to load all the URI's provided as command line arguments.
 *please note that as per the auto grader example on the crossover website . The program takes only URI's 
 *as command line argument and not the output path , so I have changed the Main file in favour of later.
 *</p>
 *<p>
 *The main thread takes all the argument as URI resource path and prints the available 
 *properties along with its type on the console.
 *example output:
 *aws_region_id, com.amazonaws.regions.Regions, US_EAST_1
 *aws_secret_key, java.lang.String, pmqnweEYvdiw7cvCdTOES48sOUvK1rGvvctBsgsa
 *hibernate_generate_statistics, java.lang.Boolean, true
 * 
 * @author deed
 */
public class Main {
    /**
     * Main method useful for your testing, this method is not tested by the grader.
     *
     * @param args
     * @throws URISyntaxException
     * @throws IOException
     */
	private static Logger log =  Logger.getLogger(Main.class);
	
    public static void main(String[] args) throws URISyntaxException, IOException {
      log.info("Starting the loading of configuration information");
        
        List<String> propertySourceUris = Arrays.asList(args);
        log.info("Property source URI's where system will try to load the properties: "+propertySourceUris);
      
        AppPropertiesManager m = new TrialAppPropertiesManager();
        AppProperties props = m.loadProps(propertySourceUris);
         m.printProperties(props, System.out);
         m.printProperties(props, new PrintStream(new FileOutputStream("MainOutput.txt")));
        
         log.info("Available properties: "+props.getKnownProperties());
         log.info("Missing properties: "+props.getMissingProperties());
         log.info("Is configuration valid ? "+props.isValid());
        
    }
}
