package com.mobtally.customer;

import com.mobtally.commands.service.CommandSourceWritePlatformService;
import com.mobtally.company.CompanyRestController;
import com.mobtally.company.service.CompanyReadPlatformService;
import com.mobtally.core.serialization.DefaultToApiJsonSerializer;
import com.mobtally.customer.service.CustomerReadPlatformService;
import com.mobtally.customer.service.CustomerWritePlatformService;
import com.mobtally.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    private final DefaultToApiJsonSerializer toApiJsonSerializer;

    private final CommandSourceWritePlatformService commandSourceWritePlatformService;

    private final CustomerReadPlatformService customerReadPlatformService;

    @Autowired
    public CustomerRestController(final DefaultToApiJsonSerializer toApiJsonSerializer,
                                  final CommandSourceWritePlatformService commandSourceWritePlatformService,
                                  final CustomerReadPlatformService customerReadPlatformService) {
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
        this.customerReadPlatformService = customerReadPlatformService;
    }

    @PostMapping(value = "/v1/customers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response loginCustomer(@RequestBody String payload) {
        Response response = new Response();
        return response;
    }
}
