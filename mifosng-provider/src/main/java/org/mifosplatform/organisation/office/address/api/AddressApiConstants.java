/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.address.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mifosplatform.portfolio.client.data.ClientData;

public class AddressApiConstants {

    public static final String ADDRESS_RESOURCE_NAME = "address";
   

        // general
    public static final String LOCAL_PARAM_NAME = "locale";
   

    // request parameters
    public static final String idParamName = "id";
    public static final String officeId = "officeId";
    public static final String officeName = "officeName";
    public static final String line1 = "line1";
    public static final String line2 = "line2";
    public static final String city = "city";
    public static final String state = "state";
    public static final String type = "type";
    //public static final String typeEnum = "typeEnum";
    

    public static final Set<String> ADDRESS_CREATE_REQUEST_DATA_PARAMETERS = new HashSet<>(
            Arrays.asList(LOCAL_PARAM_NAME, officeId, line1, line2,
                    city, state, type));

    public static final Set<String> ADDRESS_UPDATE_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(LOCAL_PARAM_NAME,
            line1, line2, city, state,type));

    /**
     * These parameters will match the class level parameters of
     * {@link ClientData}. Where possible, we try to get response parameters to
     * match those of request parameters.
     */
    public static final Set<String> ADDRESS_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(idParamName, officeId,
            line1, line2, city, state, type));

    
    }