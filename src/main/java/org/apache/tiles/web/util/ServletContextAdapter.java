/*
 * $Id: ServletContextAdapter.java 1058093 2011-01-12 11:49:02Z apetrelli $
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
package org.apache.tiles.web.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.FilterRegistration.Dynamic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;

/**
 * Adapts a servlet config and a servlet context to become a unique servlet
 * context.
 *
 * @version $Rev: 1058093 $ $Date: 2011-01-12 22:49:02 +1100 (Wed, 12 Jan 2011) $
 */
@SuppressWarnings("deprecation")
public class ServletContextAdapter implements ServletContext {

    /**
     * The root context to use.
     */
    private ServletContext rootContext;

    /**
     * The union of init parameters of {@link ServletConfig} and
     * {@link ServletContext}.
     */
    private Hashtable<String, String> initParameters;


    /**
     * Constructor.
     *
     * @param config The servlet configuration object.
     */
    @SuppressWarnings("unchecked")
    public ServletContextAdapter(ServletConfig config) {
        this.rootContext = config.getServletContext();
        initParameters = new Hashtable<String, String>();
        Enumeration<String> enumeration = rootContext
                .getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            initParameters.put(paramName, rootContext
                    .getInitParameter(paramName));
        }
        enumeration = config.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            initParameters.put(paramName, config.getInitParameter(paramName));
        }
    }

    /** {@inheritDoc} */
    public ServletContext getContext(String string) {
        return rootContext.getContext(string);
    }

    /** {@inheritDoc} */
    public int getMajorVersion() {
        return rootContext.getMajorVersion();
    }

    /** {@inheritDoc} */
    public int getMinorVersion() {
        return rootContext.getMinorVersion();
    }

    /** {@inheritDoc} */
    public String getMimeType(String string) {
        return rootContext.getMimeType(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes" })
    public Set getResourcePaths(String string) {
        return rootContext.getResourcePaths(string);
    }

    /** {@inheritDoc} */
    public URL getResource(String string) throws MalformedURLException {
        return rootContext.getResource(string);
    }

    /** {@inheritDoc} */
    public InputStream getResourceAsStream(String string) {
        return rootContext.getResourceAsStream(string);
    }

    /** {@inheritDoc} */
    public RequestDispatcher getRequestDispatcher(String string) {
        return rootContext.getRequestDispatcher(string);
    }

    /** {@inheritDoc} */
    public RequestDispatcher getNamedDispatcher(String string) {
        return rootContext.getNamedDispatcher(string);
    }

    /** {@inheritDoc} */
    public Servlet getServlet(String string) throws ServletException {
        //  return rootContext.getServlet(string);
    	return null;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getServlets() {
       // return rootContext.getServlets();  //To change body of implemented methods use File | Settings | File Templates.
    	return null;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getServletNames() {
       // return rootContext.getServletNames();
        return null;
    }

    /** {@inheritDoc} */
    public void log(String string) {
        rootContext.log(string);
    }

    /** {@inheritDoc} */
    public void log(Exception exception, String string) {
        // rootContext.log(exception, string);\
        rootContext.log(string);
    }

    /** {@inheritDoc} */
    public void log(String string, Throwable throwable) {
        rootContext.log(string, throwable);
    }

    /** {@inheritDoc} */
    public String getRealPath(String string) {
        return rootContext.getRealPath(string);
    }

    /** {@inheritDoc} */
    public String getServerInfo() {
        return rootContext.getServerInfo();
    }

    /** {@inheritDoc} */
    public String getInitParameter(String string) {
        return initParameters.get(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getInitParameterNames() {
        return initParameters.keys();
    }

    /** {@inheritDoc} */
    public Object getAttribute(String string) {
        return rootContext.getAttribute(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getAttributeNames() {
        return rootContext.getAttributeNames();
    }

    /** {@inheritDoc} */
    public void setAttribute(String string, Object object) {
        rootContext.setAttribute(string, object);
    }

    /** {@inheritDoc} */
    public void removeAttribute(String string) {
        rootContext.removeAttribute(string);
    }

    /** {@inheritDoc} */
    public String getServletContextName() {
        return rootContext.getServletContextName();
    }

    /** {@inheritDoc} */
    public String getContextPath() {
        return rootContext.getContextPath();
    }

	@Override
	public Dynamic addFilter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dynamic addFilter(String arg0, Filter arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dynamic addFilter(String arg0, Class<? extends Filter> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jakarta.servlet.ServletRegistration.Dynamic addJspFile(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends EventListener> void addListener(T arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(Class<? extends EventListener> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public jakarta.servlet.ServletRegistration.Dynamic addServlet(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jakarta.servlet.ServletRegistration.Dynamic addServlet(String arg0, Servlet arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jakarta.servlet.ServletRegistration.Dynamic addServlet(String arg0, Class<? extends Servlet> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Filter> T createFilter(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends EventListener> T createListener(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Servlet> T createServlet(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void declareRoles(String... arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEffectiveMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEffectiveMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterRegistration getFilterRegistration(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResponseCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletRegistration getServletRegistration(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionCookieConfig getSessionCookieConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSessionTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getVirtualServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInitParameter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRequestCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResponseCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionTrackingModes(Set<SessionTrackingMode> arg0) {
		// TODO Auto-generated method stub
		
	}
}
