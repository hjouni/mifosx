package org.mifosplatform.infrastructure.accountnumberformat.handler;

import javax.transaction.Transactional;

import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.accountnumberformat.service.AccountNumberFormatWritePlatformService;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountNumberFormatCommandHandler implements NewCommandSourceHandler {

    private final AccountNumberFormatWritePlatformService accountNumberFormatWritePlatformService;

    @Autowired
    public CreateAccountNumberFormatCommandHandler(final AccountNumberFormatWritePlatformService accountNumberFormatWritePlatformService) {
        this.accountNumberFormatWritePlatformService = accountNumberFormatWritePlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand command) {
        return this.accountNumberFormatWritePlatformService.createAccountNumberFormat(command);
    }

}