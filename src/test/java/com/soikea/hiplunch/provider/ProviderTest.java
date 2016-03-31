package com.soikea.hiplunch.provider;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class ProviderTest<T extends Provider> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private T provider;

    @SuppressWarnings("unchecked")
    private void initProvider() {
        ParameterizedType pt
            = (ParameterizedType) getClass().getGenericSuperclass();
        String parameterClassName
            = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
        try {
            provider = (T) Class.forName(parameterClassName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFeed() {
        String feed = getProvider().processFeed();
        log.debug(feed);
        Assert.assertNotNull(feed);
    }

    protected Provider getProvider() {
        if (provider == null) { initProvider(); }
        return provider;
    }
}
