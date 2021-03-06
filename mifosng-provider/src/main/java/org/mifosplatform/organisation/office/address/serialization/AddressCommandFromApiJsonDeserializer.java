/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.address.serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.organisation.office.address.api.AddressApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer of JSON for office API.
 */
@Component
public final class AddressCommandFromApiJsonDeserializer {

    /**
     * The parameters supported for this command.
     */
    

    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public AddressCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, AddressApiConstants.ADDRESS_CREATE_REQUEST_DATA_PARAMETERS);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("office");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        final String line1 = this.fromApiJsonHelper.extractStringNamed(AddressApiConstants.line1, element);
        baseDataValidator.reset().parameter(AddressApiConstants.line1).value(line1).notBlank().notExceedingLengthOf(100);
        
        final String line2 = this.fromApiJsonHelper.extractStringNamed(AddressApiConstants.line2, element);
        baseDataValidator.reset().parameter(AddressApiConstants.line2).value(line2).notBlank().notExceedingLengthOf(100);
        
        final String city = this.fromApiJsonHelper.extractStringNamed(AddressApiConstants.city, element);
        baseDataValidator.reset().parameter(AddressApiConstants.city).value(city).notBlank().notExceedingLengthOf(100);
        
        final String state = this.fromApiJsonHelper.extractStringNamed(AddressApiConstants.state, element);
        baseDataValidator.reset().parameter(AddressApiConstants.state).value(city).notBlank().notExceedingLengthOf(100);
        final String type = this.fromApiJsonHelper.extractStringNamed(AddressApiConstants.type, element);
        baseDataValidator.reset().parameter(AddressApiConstants.type).value(type).notBlank().notExceedingLengthOf(100);

        if (this.fromApiJsonHelper.parameterExists("externalId", element)) {
            final String externalId = this.fromApiJsonHelper.extractStringNamed("externalId", element);
            baseDataValidator.reset().parameter("externalId").value(externalId).notExceedingLengthOf(100);
        }

        if (this.fromApiJsonHelper.parameterExists("parentId", element)) {
            final Long parentId = this.fromApiJsonHelper.extractLongNamed("parentId", element);
            baseDataValidator.reset().parameter("parentId").value(parentId).notNull().integerGreaterThanZero();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

    public void validateForUpdate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, AddressApiConstants.ADDRESS_CREATE_REQUEST_DATA_PARAMETERS);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("office");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        if (this.fromApiJsonHelper.parameterExists("name", element)) {
            final String name = this.fromApiJsonHelper.extractStringNamed("name", element);
            baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(100);
        }
        if (this.fromApiJsonHelper.parameterExists("address", element)) {
            final String address = this.fromApiJsonHelper.extractStringNamed("address", element);
            baseDataValidator.reset().parameter("address").value(address).notBlank().notExceedingLengthOf(100);
        }

        if (this.fromApiJsonHelper.parameterExists("openingDate", element)) {
            final LocalDate openingDate = this.fromApiJsonHelper.extractLocalDateNamed("openingDate", element);
            baseDataValidator.reset().parameter("openingDate").value(openingDate).notNull();
        }

        if (this.fromApiJsonHelper.parameterExists("externalId", element)) {
            final String externalId = this.fromApiJsonHelper.extractStringNamed("externalId", element);
            baseDataValidator.reset().parameter("externalId").value(externalId).notExceedingLengthOf(100);
        }

        if (this.fromApiJsonHelper.parameterExists("parentId", element)) {
            final Long parentId = this.fromApiJsonHelper.extractLongNamed("parentId", element);
            baseDataValidator.reset().parameter("parentId").value(parentId).notNull().integerGreaterThanZero();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }
}