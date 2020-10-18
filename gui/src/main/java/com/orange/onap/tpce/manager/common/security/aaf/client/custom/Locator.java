/**
 * ============LICENSE_START====================================================
 * org.onap.aaf
 * ===========================================================================
 * Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
 * ===========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END====================================================
 */

package com.orange.onap.tpce.manager.common.security.aaf.client.custom;

import org.onap.aaf.cadi.LocatorException;

public interface Locator<T> {
    T get(Locator.Item item) throws LocatorException;

    boolean hasItems();

    void invalidate(Locator.Item item) throws LocatorException;

    Locator.Item best() throws LocatorException;

    Item first() throws LocatorException;

    Item next(Item item) throws LocatorException;

    boolean refresh();

    void destroy();

    interface Item {
    }

}
