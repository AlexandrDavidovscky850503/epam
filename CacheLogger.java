package App.web;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class CacheLogger implements CacheEventListener<Object, Object> {
    private static Logger logger = Logger.getLogger("CacheLogger");
    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        logger.severe("a");
    }
}
