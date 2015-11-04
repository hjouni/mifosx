package org.mifosplatform.organisation.office.address.handler;
import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.organisation.office.address.sevice.AddressWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "ADDRESS", action = "CREATE")
public class CreateAddressHandler implements NewCommandSourceHandler{
    

      
        private final AddressWritePlatformService addressWritePlatformService;

        @Autowired
        public CreateAddressHandler(final AddressWritePlatformService addressWritePlatformService) {
            this.addressWritePlatformService = addressWritePlatformService;
        }

        @Transactional
        @Override
        public CommandProcessingResult processCommand(final JsonCommand command) {

            return this.addressWritePlatformService.createAddress(command.entityId(),command);
        }
    }

