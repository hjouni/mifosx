package org.mifosplatform.organisation.office.address.sevice;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface AddressWritePlatformService {
    
        CommandProcessingResult createAddress(Long officeId,JsonCommand command);

        CommandProcessingResult updateAddress(Long officeId,Long id, JsonCommand command);

        //CommandProcessingResult officeTransaction(JsonCommand command);

        CommandProcessingResult deleteAddressTransaction(Long officeId, Long id, JsonCommand command);
  
}
