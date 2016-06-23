package com.crossover.trial.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.crossover.trial.properties.model.RequiredProperties;
import com.crossover.trial.properties.utils.Utility;

/**
 * <h1>TrialAppProperties </h1>
 * <p>
 * Class implementing AppProperties.All the loaded properties are stored into the static map and methods are provided to 
 * retrieve configuration information , get  property by providing its name,get the list of known properties , get 
 * the list of missing properties and clearing all the properties .
 *
 * note: a default constructor is required.
 *
 * @author deedsing
 */
public class TrialAppProperties implements AppProperties {

   private Map<String,Object> map= new TreeMap<String,Object>();
   private boolean isConfigValid = false;
	
	TrialAppProperties(){
		/* Set all mandatory keys and defaulted to null.*/
		for(RequiredProperties r :RequiredProperties.values()){
			String key = r.name();
			key=Utility.neutralizeKey(key); /* key stored as lowercase and '.' replaced with '_' */ 
			this.map.put(key, null);
		}
	}
	
	
	@Override
    public List<String> getMissingProperties() {
        List<String> list = new ArrayList<String>();
        for(RequiredProperties r :RequiredProperties.values()){
			String key = r.name();
			key=Utility.neutralizeKey(key); /* key stored as lowercase and '.' replaced with '_' */ 
			if(map.get(key)==null){
				list.add(key);
			}
		}
    
	return list;
	}
	

    @Override
    public List<String> getKnownProperties() {
    	List<String> list = new ArrayList<String>();
    	for (Entry<String, Object> entry : map.entrySet()) {
		    if(entry.getValue()!=null)
		    	list.add(entry.getKey());
		}
    	return list;

    }

    @Override
    public boolean isValid() {
    	List<String> list = getMissingProperties();
    	if (list.isEmpty())
    		isConfigValid=true;
        return isConfigValid;
    }

    @Override
    public void clear() {
       map.clear();
    }

    @Override
    public Object get(String key) {
        Object obj = map.get(Utility.neutralizeKey(key));
    	return obj;
    }
    
    public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void addMap(Map<String, Object> map) {
		this.map.putAll(map);
	}
	public boolean isConfigValid() {
		return isConfigValid;
	}

	public void setConfigValid(boolean isConfigValid) {
		this.isConfigValid = isConfigValid;
	}

	
}

