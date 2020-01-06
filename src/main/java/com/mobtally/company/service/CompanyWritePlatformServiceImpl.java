package com.mobtally.company.service;

import com.mobtally.company.CompanyApiConstants;
import com.mobtally.constants.TallyConstants;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.api.JsonCommand;
import com.mobtally.dto.CompanyDTO;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CompanyWritePlatformServiceImpl implements CompanyWritePlatformService {

    @Override
    public CommandProcessingResult createCompany(JsonCommand command) {
        String companyName = command.stringValueOfParameterNamed(CompanyApiConstants.COMPANY_NAME_REQUEST_PARAM);
        int directoryId = 10000;
        String companyDir = TallyConstants.TALLY_DATA_PATH + File.separator + directoryId;
        File directory = new File(companyDir);
        if(!directory.exists()) {
            boolean isCreated = directory.mkdir();
        }
        CompanyDTO companyDTO = new CompanyDTO(companyName);
        CompanyReadPlatformServiceImpl.companyDTOS.add(companyDTO);
        CommandProcessingResult commandProcessingResult = new CommandProcessingResult();
        return commandProcessingResult;
    }
}
