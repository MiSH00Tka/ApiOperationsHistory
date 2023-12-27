package ru.netology.vikhlyaev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vikhlyaev.OperationHistoryApiApplicationTest;
import ru.netology.vikhlyaev.config.OperationProperties;
import ru.netology.vikhlyaev.entity.Currency;
import ru.netology.vikhlyaev.entity.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private OperationProperties properties;
    @Autowired
    private StatementService statementService;

    @Test
    public void assertThatAsyncInputOperationServiceWorksRight() throws InterruptedException {
        Operation operation = new Operation(1, 20, Currency.RUB, "Coffee", 2);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(2L * properties.getSleepMilliSeconds());

        Operation serviceOperation = statementService.getOperation(operation.getCustomerId(), 0);
        assertEquals(operation, serviceOperation);
        assertEquals(operation.getSum(), serviceOperation.getSum());
        assertEquals(operation.getCurrency(), serviceOperation.getCurrency());
        assertEquals(operation.getMerchant(), serviceOperation.getMerchant());
        assertEquals(operation.getId(), serviceOperation.getId());
    }
}