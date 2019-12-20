package com.mobtally.company;

import com.mobtally.commands.service.CommandSourceWritePlatformService;
import com.mobtally.company.service.CompanyReadPlatformService;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.CommandWrapper;
import com.mobtally.core.CommandWrapperBuilder;
import com.mobtally.core.serialization.DefaultToApiJsonSerializer;
import com.mobtally.tallypackage.TallyPackage;
import com.mobtally.tallypackage.TallyPackageException;
import com.mobtally.tallypackage.TallyPackageParser;
import com.mobtally.tallypackage.base.Envelope;
import com.mobtally.tallypackage.base.ImportData;
import com.mobtally.tallypackage.base.TallyMessage;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

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

    @PostMapping(value = "/v1/companies", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public TallyPackage createCompany(final String apiRequestBodyAsJson) {
        final CommandWrapper wrapper = new CommandWrapperBuilder()
                .createCompany(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(wrapper);
        /*try {
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
        }*/
        try {
            String xml = "<ENVELOPE>\n" +
                    "  <IMPORTDATA>\n" +
                    "    <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
                    "    </TALLYMESSAGE>\n" +
                    "  </IMPORTDATA>\n" +
                    "</ENVELOPE>";
            return TallyPackageParser.getFromXml(xml);
        } catch (TallyPackageException e) {
            e.printStackTrace();
        }
        return null;//this.toApiJsonSerializer.serialize(result);
    }

    String marshal() throws Exception {
        Envelope root = new Envelope();
        ImportData create = new ImportData();
        root.setImportData( create );
        create.setTallyMessage(new TallyMessage());
        JAXBContext jc = JAXBContext.newInstance( Envelope.class );
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        m.marshal( root, writer);
        return writer.toString();
    }

}
