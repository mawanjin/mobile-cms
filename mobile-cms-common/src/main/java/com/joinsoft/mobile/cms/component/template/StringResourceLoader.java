package com.joinsoft.mobile.cms.component.template;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * xingsen@join-cn.com
 */
public class StringResourceLoader extends ResourceLoader {
    @Override
    public void init(ExtendedProperties configuration) {

    }

    @Override
    public InputStream getResourceStream(String source) throws ResourceNotFoundException {
        return new ByteArrayInputStream(source.getBytes());
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return true;
    }

    @Override
    public long getLastModified(Resource resource) {
        return System.currentTimeMillis();
    }
}
