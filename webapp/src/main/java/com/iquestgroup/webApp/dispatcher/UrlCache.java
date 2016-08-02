package com.iquestgroup.webApp.dispatcher;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class which acts as a cache for incoming request URL's. Internally it uses a {@link
 * ConcurrentHashMap} to keep the mapping between URL's and their cachedRequest.
 *
 * @author Stefan Pamparau
 */
public class UrlCache {

    private static final Logger logger = Logger.getLogger(UrlCache.class);

    private Map<String, CachedRequest> cache;

    public UrlCache() {
        cache = new ConcurrentHashMap<>();
    }

    public CachedRequest get(String url) {
        logger.info("Returning value with key: " + url);
        return cache.get(url);
    }

    public void put(String url, CachedRequest cachedRequest) {
        logger.info("Inserting key: " + url + " with value: " + cachedRequest);
        cache.put(url, cachedRequest);
    }

    public boolean containsKey(String url) {
        logger.info("Verifying if cache contains value with key: " + url);
        return cache.containsKey(url);
    }
}
