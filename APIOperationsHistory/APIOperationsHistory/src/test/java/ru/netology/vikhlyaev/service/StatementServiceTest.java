package ru.netology.vikhlyaev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vikhlyaev.OperationHistoryApiApplicationTest;
import ru.netology.vikhlyaev.entity.Currency;
import ru.netology.vikhlyaev.entity.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;

    @Test
    public void assertThatStatementServiceWorksRight() {
        int operationId = 2;
        int operationClientId = 0;
        int clientId = 1;
        int operationSum = 100;
        Currency operationCurrency = Currency.USD;
        String operationMerchant = "Coffee";

        Operation operation = new Operation(operationId, operationSum, operationCurrency, operationMerchant, clientId);
        statementService.setOperation(clientId, operation);
        Operation serviceOperation = statementService.getOperation(clientId, operationClientId);

        assertEquals(operation, serviceOperation);
        assertEquals(operationId, serviceOperation.getId());
        assertEquals(operationSum, serviceOperation.getSum());
        assertEquals(operationCurrency, serviceOperation.getCurrency());
        assertEquals(operationMerchant, serviceOperation.getMerchant());
    }
}