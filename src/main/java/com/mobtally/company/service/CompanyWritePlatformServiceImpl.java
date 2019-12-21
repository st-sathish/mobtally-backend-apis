package com.mobtally.company.service;

import com.mobtally.company.CompanyApiConstants;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.api.JsonCommand;
import com.mobtally.dto.CompanyDTO;
import org.springframework.stereotype.Service;

@Service
public class CompanyWritePlatformServiceImpl implements CompanyWritePlatformService {

    @Override
    public CommandProcessingResult save(JsonCommand command) {
        String companyName = command.stringValueOfParameterNamed(CompanyApiConstants.COMPANY_NAME_REQUEST_PARAM);
        CompanyDTO companyDTO = new CompanyDTO(companyName);
        CompanyReadPlatformServiceImpl.companyDTOS.add(companyDTO);
        CommandProcessingResult commandProcessingResult = new CommandProcessingResult();
        return commandProcessingResult;
    }
}
