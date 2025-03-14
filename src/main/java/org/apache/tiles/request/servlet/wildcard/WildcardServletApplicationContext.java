/*
 * $Id: WildcardServletApplicationContext.java 1306435 2012-03-28 15:39:11Z nlebas $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.tiles.request.servlet.wildcard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.tiles.request.ApplicationResource;
import org.apache.tiles.request.locale.URLApplicationResource;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

/**
 * Servlet-based implementation of the TilesApplicationContext interface that
 * can resolve resources even using wildcards.
 *
 * @version $Rev: 1306435 $ $Date: 2012-03-29 02:39:11 +1100 (Thu, 29 Mar 2012) $
 */
public class WildcardServletApplicationContext extends ServletApplicationContext {

    /**
     * The pattern resolver.
     */
    protected ResourcePatternResolver resolver;

    /**
     * Constructor.
     *
     * @param servletContext The servlet context.
     */
    public WildcardServletApplicationContext(ServletContext servletContext) {
        super(servletContext);
        resolver = new ServletContextResourcePatternResolver(servletContext);
    }

    /** {@inheritDoc} */
    @Override
    public ApplicationResource getResource(String localePath) {
        ApplicationResource retValue = null;
        Collection<ApplicationResource> urlSet = getResources(localePath);
        if (urlSet != null && !urlSet.isEmpty()) {
            retValue = urlSet.iterator().next();
        }
        return retValue;
    }

    /** {@inheritDoc} */
    @Override
    public ApplicationResource getResource(ApplicationResource base, Locale locale) {
        ApplicationResource retValue = null;
        Collection<ApplicationResource> urlSet = getResources(base.getLocalePath(locale));
        if (urlSet != null && !urlSet.isEmpty()) {
            retValue = urlSet.iterator().next();
        }
        return retValue;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<ApplicationResource> getResources(String path) {
        Resource[] resources;
        try {
            resources = resolver.getResources(path);
        } catch (IOException e) {
            return Collections.<ApplicationResource> emptyList();
        }
        Collection<ApplicationResource> resourceList = new ArrayList<ApplicationResource>();
        if (resources != null && resources.length > 0) {
            for (int i = 0; i < resources.length; i++) {
                URL url;
                try {
                    url = resources[i].getURL();
                    resourceList.add(new URLApplicationResource(url.toExternalForm(), url));
                } catch (IOException e) {
                    // shouldn't happen with the kind of resources we're using
                    throw new IllegalArgumentException("no URL for " + resources[i].toString(), e);
                }
            }
        }
        return resourceList;
    }
}
