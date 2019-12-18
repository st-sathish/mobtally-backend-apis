package com.mobtally.company;

import com.mobtally.company.service.CompanyService;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapperBuilder;
import com.mobtally.core.serialization.DefaultToApiJsonSerializer;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api
public class CompanyRestController implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    private final CompanyService companyService;

    private final DefaultToApiJsonSerializer toApiJsonSerializer;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Registered CompanyRestController");
    }

    @Autowired
    public CompanyRestController(final CompanyService companyService,
                                 final DefaultToApiJsonSerializer toApiJsonSerializer) {
        this.companyService = companyService;
        this.toApiJsonSerializer = toApiJsonSerializer;
    }

    @GetMapping("/v1/companies")
    public String fetchCompanyList() {
        return "";
    }

    @PostMapping("/v1/companies")
    public String createCompany(final String apiRequestBodyAsJson) {
        final CommandWrapperBuilder builder = new CommandWrapperBuilder().createCompany(apiRequestBodyAsJson);
        CommandProcessingResult result = companyService.save(builder);
        return this.toApiJsonSerializer.serialize(result);
    }


}
