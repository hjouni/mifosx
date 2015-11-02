package org.mifosplatform.organisation.office.address.sevice;
import java.util.Collection;

import org.mifosplatform.organisation.office.address.data.AddressData;


public interface AddressReadPlatformService {
    Collection<AddressData> retrieveAddresses(Long officeId);

    //Collection<AddressData> retrieveAllOfficesForDropdown();

    AddressData retrieveAddress(Long officeId,Long id);

    AddressData retrieveNewAddressTemplate();

    //Collection<AddressData> retrieveAllowedParents(Long officeId);

    }
