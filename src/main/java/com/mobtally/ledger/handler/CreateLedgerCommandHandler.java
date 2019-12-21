package com.mobtally.ledger.handler;

import com.mobtally.commands.annotation.CommandType;
import com.mobtally.core.CommandProcessingResult;
import com.mobtally.core.NewCommandSourceHandler;
import com.mobtally.core.api.JsonCommand;
import org.springframework.stereotype.Service;

@Service
@CommandType(action = "CREATE", entity = "LEDGER")
public class CreateLedgerCommandHandler implements NewCommandSourceHandler {

    @Override
    public CommandProcessingResult processCommand(JsonCommand command) {
        return null;
    }
}
