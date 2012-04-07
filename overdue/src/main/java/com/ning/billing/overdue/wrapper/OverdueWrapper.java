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

package com.ning.billing.overdue.wrapper;

import com.ning.billing.catalog.api.CatalogApiException;
import com.ning.billing.catalog.api.overdue.BillingState;
import com.ning.billing.catalog.api.overdue.OverdueError;
import com.ning.billing.catalog.api.overdue.OverdueState;
import com.ning.billing.catalog.api.overdue.OverdueStateSet;
import com.ning.billing.catalog.api.overdue.Overdueable;
import com.ning.billing.overdue.applicator.OverdueStateApplicator;
import com.ning.billing.overdue.calculator.BillingStateCalculator;
import com.ning.billing.util.clock.Clock;
import com.ning.billing.util.overdue.OverdueAccessApi;

public class OverdueWrapper<T extends Overdueable> {
    private final T overdueable;
    private final OverdueAccessApi api;
    private final Clock clock;
    private final OverdueStateSet<T> overdueStateSet;
    private final BillingStateCalculator<T> billingStateCalcuator;
    private final OverdueStateApplicator<T> overdueStateApplicator;

    public OverdueWrapper(T overdueable, OverdueAccessApi api,
            OverdueStateSet<T> overdueStateSet,
            Clock clock,
            BillingStateCalculator<T> billingStateCalcuator,
            OverdueStateApplicator<T> overdueStateApplicator) {
        this.overdueable = overdueable;
        this.overdueStateSet = overdueStateSet;
        this.api = api;
        this.clock = clock;
        this.billingStateCalcuator = billingStateCalcuator;
        this.overdueStateApplicator = overdueStateApplicator;
    }

    public OverdueState<T> refresh() throws OverdueError, CatalogApiException {
        OverdueState<T> nextOverdueState;
        BillingState<T> billingState = billingStateCalcuator.calculateBillingState(overdueable);
        String previousOverdueStateName = api.getOverdueStateNameFor(overdueable);
        nextOverdueState = overdueStateSet.calculateOverdueState(billingState, clock.getUTCNow());
        if(!previousOverdueStateName.equals(nextOverdueState.getName())) {
            overdueStateApplicator.apply(overdueable, nextOverdueState, nextOverdueState, overdueStateSet.dateOfNextCheck(billingState, clock.getUTCNow())); 
        }

        return nextOverdueState;
    }
}