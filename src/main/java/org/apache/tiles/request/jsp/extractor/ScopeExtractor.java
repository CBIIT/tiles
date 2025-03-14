/*
 * $Id: ScopeExtractor.java 1066790 2011-02-03 12:06:20Z apetrelli $
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
package org.apache.tiles.request.jsp.extractor;

import java.util.Enumeration;

import javax.servlet.jsp.JspContext;

import org.apache.tiles.request.attribute.AttributeExtractor;

/**
 * Extracts attributes from a numbered scope from {@link JspContext}.
 *
 * @version $Rev: 1066790 $ $Date: 2011-02-03 23:06:20 +1100 (Thu, 03 Feb 2011) $
 */
public class ScopeExtractor implements AttributeExtractor {

    /**
     * The JSP context.
     */
    private JspContext context;

    /**
     * The scope number to use.
     */
    private int scope;

    /**
     * Constructor.
     *
     * @param context The JSP context.
     * @param scope The scope number.
     */
    public ScopeExtractor(JspContext context, int scope) {
        this.context = context;
        this.scope = scope;
    }

    @Override
    public void removeValue(String name) {
        context.removeAttribute(name, scope);
    }

    @Override
    public Enumeration<String> getKeys() {
        return context.getAttributeNamesInScope(scope);
    }

    @Override
    public Object getValue(String key) {
        return context.getAttribute(key, scope);
    }

    @Override
    public void setValue(String key, Object value) {
        context.setAttribute(key, value, scope);
    }
}
