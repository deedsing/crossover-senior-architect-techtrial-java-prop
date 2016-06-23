package com.crossover.trial.properties.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.crossover.trial.properties.model.PropertyTypeMap;

/**
 * Parser class provides functionalities to read data and create Map objects.It also has additional
 * responsibility of validating the property type.
 * 
 * 
 * @author deedsing
 *
 */
public class Parser {

	private static Logger log = Logger.getLogger(Parser.class);

	public static Map<String, Object> parseProperties(String s) {
		log.debug("The string contents received: \n" + s);
		Map<String, Object> out = new HashMap<String, Object>();
		String[] lines = s.split("\n");
		for (String s1 : lines) {
			//s1=s1.trim();
			if(!s1.trim().startsWith("#")&&!s1.trim().isEmpty()&&s1.contains("=")){   /*excluding file comments and assuming property seperator '=' */
			String[] parts = s1.split("=");
			String key = Utility.neutralizeKey(parts[0]);
			Object value = Objects.toString(parts[1],null).trim();
			value = parseTypeForkey(key, value);
			out.put(key, value);
			}
		}
		return out;

	}

	public static Map<String, Object> parseJson(String s) {
		log.debug("The string contents received: \n" + s);
		Map<String, Object> out = new HashMap<String, Object>();
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(s);
			JSONObject json = (JSONObject) obj;
			for (Iterator iterator = json.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				Object value =Objects.toString(json.get(key), null);
				key = Utility.neutralizeKey(key);
				value = parseTypeForkey(key, value);
				out.put(key, value);
			}

		} catch (ParseException e) {
			log.error("Not able to parse json String " + s);
			e.printStackTrace();
		}

		return out;
	}

	private static Object parseTypeForkey(String key, Object value) {
		key = Utility.neutralizeKey(key);
		String type = PropertyTypeMap.getTypeForProperty(key);
		if (type == null)
			return value; /*
							 * return the object as it is , in most cases the type
							 * will be string
							 */
		if (value == null)
			return null; /* value is not set for the property */
		else {
			// here we will try to set the values as per the Property map we
			// have already defined as enum.

			if (type != value.getClass()
					.getName()) { /*
									 * property type is not equal to the expected
									 * type
									 */

				if (value.getClass()
						.equals(String.class)) { /* for String type */

					value = parseObjectTypeFromString(value.toString().trim(), type);
				} else { /* for other type , nothing needed :) */
					
					 
				}

			}

		}

		return value;
	}

	private static Object parseObjectTypeFromString(String value, String type) {
		try {
			if (String.class.getName().equals(type))
				return value;
			else if (Integer.class.getName().equals(type))
				return Integer.parseInt(value);
			else if (Byte.class.getName().equals(type))
				return Byte.parseByte(value);
			else if (Short.class.getName().equals(type))
				return Short.parseShort(value);
			else if (Double.class.getName().equals(type))
				return Double.parseDouble(value);
			else if (Long.class.getName().equals(type))
				return Long.parseLong(value);
			else if (Boolean.class.getName().equals(type))
				return Boolean.parseBoolean(value);
			else if (Float.class.getName().equals(type))
				return Float.parseFloat(value);
			else if (Character.class.getName().equals(type))
				return value.charAt(0);
			else if (Regions.class.getName().equals(type))
				return Regions.valueOf(Utility.neutralizeRegion(value));
		} catch (Exception e) {
			e.printStackTrace();
			value = null;
		}
		return value;
	}

}
