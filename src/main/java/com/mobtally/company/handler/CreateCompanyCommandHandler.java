package com.mobtally.company.handler;

import com.mobtally.commands.annotation.CommandType;
import com.mobtally.company.service.CompanyWritePlatformService;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.NewCommandSourceHandler;
import com.mobtally.core.api.JsonCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@CommandType(action = "CREATE", entity = "COMPANY")
public class CreateCompanyCommandHandler implements NewCommandSourceHandler {

    private final CompanyWritePlatformService companyWritePlatformService;

    @Autowired
    private CreateCompanyCommandHandler(final CompanyWritePlatformService companyWritePlatformService) {
        this.companyWritePlatformService = companyWritePlatformService;
    }

    @Override
    public CommandProcessingResult processCommand(JsonCommand command) {
        return this.companyWritePlatformService.save(command);
    }
}
