package com.mobtally.company.service;

import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapperBuilder;

public interface CompanyService {

    CommandProcessingResult save(CommandWrapperBuilder builder);
}
