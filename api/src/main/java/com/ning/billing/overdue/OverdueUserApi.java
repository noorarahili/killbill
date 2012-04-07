/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.overdue;

import com.ning.billing.catalog.api.CatalogApiException;
import com.ning.billing.catalog.api.overdue.BillingState;
import com.ning.billing.catalog.api.overdue.OverdueError;
import com.ning.billing.catalog.api.overdue.OverdueState;
import com.ning.billing.catalog.api.overdue.Overdueable;

public interface OverdueUserApi {

    public <T extends Overdueable> OverdueState<T> refreshOverdueStateFor(T overdueable) throws OverdueError, CatalogApiException;

    public <T extends Overdueable> void setOverrideBillingStateForAccount(T overdueable, BillingState<T> state) throws OverdueError;

    public <T extends Overdueable> OverdueState<T> getOverdueStateFor(T overdueable) throws OverdueError;
}
