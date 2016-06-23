package com.crossover.trial.properties.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This enum stores information map of property name and its valid data type.
 * @author deedsing
 *
 */
public enum PropertyTypeMap {

	AUTH_ENDPOINT_URI("auth_endpoint_uri","java.lang.String"),
	AWS_ACCESS_KEY("aws_access_key", "java.lang.String"),
	AWS_ACCOUNT_ID("aws_account_id", "java.lang.Integer"),
	AWS_REGION_ID("aws_region_id", "com.amazonaws.regions.Regions"),
	AWS_SECRET_KEY("aws_secret_key", "java.lang.String"),
	HIBERNATE_GENERATE_STATISTICS("hibernate_generate_statistics", "java.lang.Boolean"),
	HIBERNATE_SHOW_SQL("hibernate_show_sql", "java.lang.Boolean"),
	JDBC_DRIVER("jdbc_driver", "java.lang.String"),
	JDBC_PASSWORD("jdbc_password", "java.lang.String"),
	JDBC_URL("jdbc_url", "java.lang.String"),
	JDBC_USERNAME("jdbc_username", "java.lang.String"),
	JOB_MAXRETRY("job_maxretry", "java.lang.Integer"),
	JOB_TIMEOUT("job_timeout", "java.lang.Integer"),
	JPA_SHOWSQL("jpa_showsql", "java.lang.Boolean"),
	SCORE_FACTOR("score_factor", "java.lang.Double"),
	SNS_BROADCAST_TOPIC_NAME("sns_broadcast_topic_name", "java.lang.String"),
	SNS_BROADCAST_VISIBILITY_TIMEOUT("sns_broadcast_visibility_timeout", "java.lang.Integer");

    private final String prop;
    private final String type;
    private static final Map<String, String> mMap = Collections.unmodifiableMap(initializeMapping());

    private PropertyTypeMap(String prop, String type) {
        this.prop = prop;
        this.type = type;
    }
    public String getProp() {
        return prop;
    }

    public String getType() {
        return type;
    }


    public static String getTypeForProperty(String prop) {
        if (mMap == null) {
            initializeMapping();
        }
        if (mMap.containsKey(prop)) {
            return mMap.get(prop);
        }
        return null;
    }

    private static Map<String, String> initializeMapping() {
    	 Map<String, String> mMap = new HashMap<String, String>();
        for (PropertyTypeMap s : PropertyTypeMap.values()) {
            mMap.put(s.prop, s.type);
        }
        return mMap;
    }
}