package com.crossover.trial.properties;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.crossover.trial.properties.exceptions.UnsupportedFileFormatException;
import com.crossover.trial.properties.exceptions.UnsupportedURIException;
import com.crossover.trial.properties.loaders.Loader;
import com.crossover.trial.properties.loaders.LoaderFactory;
import com.crossover.trial.properties.model.AcceptedFileFormats;
import com.crossover.trial.properties.model.AcceptedURI;
import com.crossover.trial.properties.utils.Utility;

/**<h1>TrialAppPropertiesManager</h1>
 * <p>
 * Class implementing AppPropertiesManager.This class provides two methods; One to load 
 * the properties from list of URI's  and other to print the properties to an output stream.
 * 
 * Note: a default constructor is required
 *
 * @author code test administrator
 */
public class TrialAppPropertiesManager implements AppPropertiesManager {

	private Logger log = Logger.getLogger(TrialAppPropertiesManager.class);
    
	
	@Override
    public AppProperties loadProps(List<String> propUris) {
       
    	TrialAppProperties appProperty = new TrialAppProperties();
    	
    	for(String uri : propUris){
    		try{
    		  validate(uri);
    		  Loader loader = LoaderFactory.getLoader(uri);
    		if (loader!=null){   			
    			log.debug("Trying to load the properties from URI path : "+uri);
    			
    				String uriType=Utility.getURIType(loader.getClass().getName());
    		        String path = Utility.getPath(uri,uriType);
    			
				log.debug("The path of the resource is "+path);
				Map<String,Object> out = loader.getProperties(path);
			    appProperty.addMap(out);
				log.debug("Loaded configuration for URI path : "+uri);}
    			}catch(UnsupportedURIException e){
    				log.error(e);
    			} catch (UnsupportedFileFormatException e) {
				   log.error(e);
				}
    		}
    		
    	return appProperty;
    }
    	
    	

    /**
     * Validate the URI string to check if it currently supported by the system or not ?
     * @param uri
     * @throws UnsupportedFileFormatException
     * @throws UnsupportedURIException
     */
    private void validate(String uri) throws UnsupportedFileFormatException, UnsupportedURIException {
    	boolean isUriValid=false;
    	boolean isFileFormatvalid=false;
    	
    	for(String validUri : AcceptedURI.list){
    		if(uri.startsWith(validUri)){
    			isUriValid=true;
    			break;
    		}
    	}
    	
    	for(String validFileFormat : AcceptedFileFormats.list){
    		if(uri.endsWith(validFileFormat)){
    			isFileFormatvalid=true;
    			break;
    		}
    	}
    	
    	if(!isUriValid)
    	   throw new UnsupportedURIException(uri);
    	if(!isFileFormatvalid)
			throw new UnsupportedFileFormatException(uri);
		
	}

  
	@Override
    public void printProperties(AppProperties props, PrintStream sync) {
        @SuppressWarnings("unchecked")
		List<String> list = (List<String>) props.getKnownProperties(); 
        for(String k : list){
        	 Object value = props.get(k);
        	 if(value!=null)
        	 sync.println( k +", "+value.getClass().getName()+", " + value);
        }
       
    }
}
