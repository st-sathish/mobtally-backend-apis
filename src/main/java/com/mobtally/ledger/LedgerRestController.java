package com.mobtally.ledger;

import com.mobtally.company.CompanyRestController;
import com.mobtally.ledger.service.LedgerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api
public class LedgerRestController implements InitializingBean  {

    private static final Logger logger = LoggerFactory.getLogger(LedgerRestController.class);

    private final LedgerService ledgerService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Registered LedgerRestController");
    }

    @Autowired
    public LedgerRestController(final LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/v1/ledgers")
    public String createLedger() {
        return "";
    }

    @GetMapping("/v1/ledgers")
    public String fetchAllLedgers() {
        return "";
    }

    @PutMapping("/v1/ledgers")
    public String alterLedger() {
        return "";
    }
}
