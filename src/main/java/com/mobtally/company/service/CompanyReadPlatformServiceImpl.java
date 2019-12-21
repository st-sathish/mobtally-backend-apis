package com.mobtally.company.service;

import com.mobtally.dto.CompanyDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyReadPlatformServiceImpl implements CompanyReadPlatformService {

    public static List<CompanyDTO> companyDTOS = new ArrayList<>();

    static {
        CompanyDTO companyDTO = new CompanyDTO("Mob Tally");
        CompanyDTO companyDTO1 = new CompanyDTO("Mob Tally1");
        companyDTOS.add(companyDTO);
        companyDTOS.add(companyDTO1);
    }

    @Override
    public List<CompanyDTO> fetchCompanies() {
        return companyDTOS;
    }
}
