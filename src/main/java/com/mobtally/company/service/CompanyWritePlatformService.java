package com.mobtally.company.service;

import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.api.JsonCommand;

public interface CompanyWritePlatformService {

    CommandProcessingResult save(JsonCommand command);
}
