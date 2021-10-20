/*
 * Copyright 2014-2015 Groupon, Inc
 * Copyright 2014-2015 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
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

package org.killbill.billing.catalog.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.killbill.billing.callcontext.MutableCallContext;
import org.killbill.billing.catalog.CatalogTestSuiteWithEmbeddedDB;
import org.killbill.billing.catalog.DefaultPlanPhasePriceOverride;
import org.killbill.billing.catalog.StandaloneCatalog;
import org.killbill.billing.catalog.api.CatalogUserApi;
import org.killbill.billing.catalog.api.Currency;
import org.killbill.billing.catalog.api.Plan;
import org.killbill.billing.catalog.api.PlanPhasePriceOverride;
import org.testng.annotations.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TestCatalogUserApi extends CatalogTestSuiteWithEmbeddedDB {

	@Inject
	protected CatalogUserApi catalogUserApi;
	
	@Inject
	protected MutableCallContext callContext;
    
	@Test(groups = "slow")
    	public void testOverrideUploadCatalog() throws Exception {

        final StandaloneCatalog catalog = getCatalog("SpyCarBasic.xml");
        
        catalogUserApi.uploadCatalog(Resources.asCharSource(Resources.getResource("org/killbill/billing/catalog/SpyCarBasic.xml"), Charsets.UTF_8).read(), callContext);
        
    }


}

