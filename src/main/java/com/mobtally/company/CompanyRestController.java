package com.mobtally.company;

import com.mobtally.commands.service.CommandSourceWritePlatformService;
import com.mobtally.company.service.CompanyReadPlatformService;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;
import com.mobtally.core.CommandWrapperBuilder;
import com.mobtally.core.serialization.DefaultToApiJsonSerializer;
import com.mobtally.tallypackage.TallyPackage;
import com.mobtally.tallypackage.TallyPackageBuilder;
import com.mobtally.tallypackage.TallyPackageBuilderFactory;
import com.mobtally.tallypackage.TallyPackageException;
import com.mobtally.tallypackage.TallyPackageParser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
@Api
public class CompanyRestController implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    private final DefaultToApiJsonSerializer toApiJsonSerializer;

    private final CommandSourceWritePlatformService commandSourceWritePlatformService;

    private final CompanyReadPlatformService companyReadPlatformService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Registered CompanyRestController");
    }

    @Autowired
    public CompanyRestController(final DefaultToApiJsonSerializer toApiJsonSerializer,
                                 final CommandSourceWritePlatformService commandSourceWritePlatformService,
                                 final CompanyReadPlatformService companyReadPlatformService) {
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
        this.companyReadPlatformService = companyReadPlatformService;
    }

    @GetMapping("/v1/companies")
    public String fetchCompanyList() {
        return "";
    }

    @PostMapping(value = "/v1/companies")
    public String createCompany(final String apiRequestBodyAsJson) {
        final CommandWrapper wrapper = new CommandWrapperBuilder()
                .createCompany(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(wrapper);
        try {
            TallyPackageBuilder tallyPackageBuilder = TallyPackageBuilderFactory.newInstance().newTallyPackageBuilder();
            TallyPackage tallyPackage = null;
            String path = "/home/iface/tally_data/ledgers.xml";
            try {
                InputStream is = new FileInputStream(path);
                tallyPackage = tallyPackageBuilder.loadFromXml(is);
            } catch (FileNotFoundException e) {
                logger.error("File not found {}", e.getMessage());
            }
            return TallyPackageParser.getAsXml(tallyPackage);
        } catch (TallyPackageException e) {
            logger.error("Tally Parse exception {}", e.getMessage());
        }
        return this.toApiJsonSerializer.serialize(result);
    }


}
