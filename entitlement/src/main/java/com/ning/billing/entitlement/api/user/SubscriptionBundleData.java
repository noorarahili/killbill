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

package com.ning.billing.entitlement.api.user;

import java.util.UUID;

import org.joda.time.DateTime;

import com.ning.billing.catalog.api.overdue.OverdueState;

public class SubscriptionBundleData implements SubscriptionBundle {

    private final UUID id;
    private final String key;
    private final UUID accountId;
    private final DateTime startDate;
    private final OverdueState<SubscriptionBundle> overdueState;

    public SubscriptionBundleData(String name, UUID accountId) {
        this(UUID.randomUUID(), name, accountId, null, null);
    }

    public SubscriptionBundleData(UUID id, String key, UUID accountId, DateTime startDate, OverdueState<SubscriptionBundle> overdueState) {
        super();
        this.id = id;
        this.key = key;
        this.accountId = accountId;
        this.startDate = startDate;
        this.overdueState = overdueState;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getAccountId() {
        return accountId;
    }


    // STEPH do we need it ? and should we return that and when is that populated/updated?
    @Override
    public DateTime getStartDate() {
        return startDate;
    }

    @Override
    public OverdueState<SubscriptionBundle> getOverdueState() {
        return overdueState;
    }
}
