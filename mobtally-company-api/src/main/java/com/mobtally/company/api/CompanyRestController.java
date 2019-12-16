package com.mobtally.company.api;

import com.mobtally.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyRestController {

    private final CompanyService companyService;

    @Autowired
    public CompanyRestController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/v1/companies")
    public String fetchCompanyList() {
        return "";
    }

    @PostMapping("/v1/companies")
    public String createCompany() {
        return "";
    }


}
