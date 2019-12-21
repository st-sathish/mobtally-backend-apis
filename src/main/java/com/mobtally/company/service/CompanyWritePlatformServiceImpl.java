package com.mobtally.company.service;

import com.mobtally.company.CompanyApiConstants;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.api.JsonCommand;
import org.springframework.stereotype.Service;

@Service
public class CompanyWritePlatformServiceImpl implements CompanyWritePlatformService {

    @Override
    public CommandProcessingResult save(JsonCommand command) {
        String companyName = command.stringValueOfParameterNamed(CompanyApiConstants.NAME_PARAM_NAME);
        return null;
    }
}
