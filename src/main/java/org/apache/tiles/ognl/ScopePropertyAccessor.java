/*
 * $Id: ScopePropertyAccessor.java 1049696 2010-12-15 20:30:10Z apetrelli $
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
package org.apache.tiles.ognl;


import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.PropertyAccessor;

import org.apache.tiles.request.Request;

/**
 * Accesses a scope.
 *
 * @version $Rev: 1049696 $ $Date: 2010-12-16 07:30:10 +1100 (Thu, 16 Dec 2010) $
 */
public class ScopePropertyAccessor implements PropertyAccessor {

    /**
     * The length of the scope suffix: "Scope".
     */
    static final int SCOPE_SUFFIX_LENGTH = 5;

    @Override
    public Object getProperty(OgnlContext context, Object target, Object name) {
        Request request = (Request) target;
        String scope = (String) name;
        if (scope.endsWith("Scope")) {
            String scopeName = scope.substring(0, scope.length() - SCOPE_SUFFIX_LENGTH);
            return request.getContext(scopeName);
        }
        return null;
    }

    @Override
    public String getSourceAccessor(OgnlContext context, Object target,
            Object index) {
        String scope = (String) index;
        if (scope.endsWith("Scope")) {
            String scopeName = scope.substring(0, scope.length() - SCOPE_SUFFIX_LENGTH);
            return ".getContext(\"" + scopeName + "\")";
        }
        return null;
    }

    @Override
    public String getSourceSetter(OgnlContext context, Object target,
            Object index) {
        return null;
    }

	@Override
	public void setProperty(OgnlContext arg0, Object arg1, Object arg2, Object arg3) throws OgnlException {
		// TODO Auto-generated method stub
		
	}

}
