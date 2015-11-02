/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.address.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

/**
 * A {@link RuntimeException} thrown when office resources are not found.
 */
public class AddressNotFoundException extends AbstractPlatformResourceNotFoundException {

    public AddressNotFoundException(final Long officeId,final Long id) {
        super("error.msg.office.id.address.id.invalid", "Office with identifier " + officeId + "and the Address" + id+ " does not exist", id);
    }
}