package com.mobtally.company.service;

import com.mobtally.dto.CompanyDTO;

import java.util.List;

public interface CompanyReadPlatformService {

    List<CompanyDTO> fetchCompanies();
}
