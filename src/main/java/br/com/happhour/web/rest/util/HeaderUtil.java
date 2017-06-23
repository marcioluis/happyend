package br.com.happhour.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 * 
 * @from jHipster
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

	private static final String APPLICATION_NAME = "happyend";

    private HeaderUtil() {
    }

	/**
	 * Create a custom header
	 * 
	 * @param message
	 * @param param
	 * @return header with the keys: X-happyend-alert and X-happyend-params
	 */
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
		headers.add("X-happyend-alert", message);
		headers.add("X-happyend-params", param);
        return headers;
    }

	/**
	 * Creates an alert with the message format:
	 * {@code application.entityName.created}
	 * 
	 * @param entityName
	 * @param param
	 * @return header with the keys: X-happyend-alert and X-happyend-params
	 */
    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

	/**
	 * Creates an alert with the message format:
	 * {@code application.entityName.updated}
	 * 
	 * @param entityName
	 * @param param
	 * @return header with the keys: X-happyend-alert and X-happyend-params
	 */
    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

	/**
	 * Creates an alert with the message format:
	 * {@code application.entityName.deleted}
	 * 
	 * @param entityName
	 * @param param
	 * @return header with the keys: X-happyend-alert and X-happyend-params
	 */
    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

	/**
	 *
	 * Create a custom error header
	 * 
	 * @param entityName
	 *            entity name to X-happyend-param
	 * @param errorKey
	 *            error key for the header X-happyend-error: error.errorKey
	 * @param defaultMessage
	 *            message to the logger
	 * @return header with the keys: X-happyend-error and X-happyend-params
	 */
    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
		headers.add("X-happyend-error", "error." + errorKey);
		headers.add("X-happyend-params", entityName);
        return headers;
    }
}
