package org.mifosplatform.organisation.office.address.sevice;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.organisation.office.address.api.AddressApiConstants;
import org.mifosplatform.organisation.office.address.domain.Address;
import org.mifosplatform.organisation.office.address.domain.AddressRepositoryWrapper;
import org.mifosplatform.organisation.office.address.serialization.AddressCommandFromApiJsonDeserializer;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.office.domain.OfficeRepository;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AddressWritePlatformJPARepositoryServiceImpl implements AddressWritePlatformService {

    @Autowired
    public AddressWritePlatformJPARepositoryServiceImpl(AddressCommandFromApiJsonDeserializer fromApiJsonDeserializer,OfficeRepositoryWrapper officeRepositoryWrapper,AddressRepositoryWrapper addressRepositoryWrapper) {
        super();
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.officeRepositoryWrapper=officeRepositoryWrapper;
        this.addressRepositoryWrapper=addressRepositoryWrapper;
    }

    private final AddressCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final OfficeRepositoryWrapper officeRepositoryWrapper;
    private final AddressRepositoryWrapper addressRepositoryWrapper;
    @Override
    public CommandProcessingResult createAddress(Long officeId, JsonCommand command) {
        try {

            this.fromApiJsonDeserializer.validateForCreate(command.json());

           // Long officeId = null;
           // officeId = command.longValueOfParameterNamed(AddressApiConstants.officeId);
               
            Office office =  officeRepositoryWrapper.findOneWithNotFoundDetection(officeId) ;       
            // pre save to generate id for use in office hierarchy
                       
            Address address = Address.fromJson(office, command);
            this.addressRepositoryWrapper.save(address);
            //office.generateHierarchy();
         
            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .withEntityId(officeId) 
                    .withSubEntityId(address.getId())
                    .withOfficeId(office.getId()) //
                    .build();
        } catch (final DataIntegrityViolationException dve) {
           // handleOfficeDataIntegrityIssues(command, dve);
            return CommandProcessingResult.empty();
        }
    }

    @Override
    public CommandProcessingResult updateAddress(Long officeId, Long id, JsonCommand command) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommandProcessingResult deleteAddressTransaction(Long officeId, Long id, JsonCommand command) {
        // TODO Auto-generated method stub
        return null;
    }

}
