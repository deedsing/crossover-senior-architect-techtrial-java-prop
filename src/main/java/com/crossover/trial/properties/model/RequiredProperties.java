package com.crossover.trial.properties.model;

/**
 * This enum stores all the required properties , if any one is not set the configuration is invalid.
 * @author deedsing
 *
 */
public enum RequiredProperties {

	JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, HIBERNATE_GENERATE_STATISTICS, HIBERNATE_SHOW_SQL, JPA_SHOWSQL, AWS_ACCESS_KEY, AWS_SECRET_KEY, AWS_ACCOUNT_ID, AWS_REGION_ID, AUTH_ENDPOINT_URI, JOB_TIMEOUT, JOB_MAXRETRY, SNS_BROADCAST_TOPIC_NAME, SNS_BROADCAST_VISIBILITY_TIMEOUT, SCORE_FACTOR

}

