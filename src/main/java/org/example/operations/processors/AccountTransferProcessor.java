package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class AccountTransferProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter sourse account id: ");
        int fromAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter destination account id: ");
        int toAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to transfer: ");
        int amountToTranfer = Integer.parseInt(scanner.nextLine());
        accountService.transfer(fromAccountId, toAccountId,amountToTranfer);
        System.out.println("Successfully transferred");

    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
