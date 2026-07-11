package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class DepositeAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public DepositeAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to deposite");
        int amountToDeposite = Integer.parseInt(scanner.nextLine());
        accountService.depositeAccount(accountId, amountToDeposite);
        System.out.println("Successfully deposite amount");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSITE;
    }
}
