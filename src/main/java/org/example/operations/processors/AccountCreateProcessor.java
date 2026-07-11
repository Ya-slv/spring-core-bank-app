package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.user.UserService;

import java.util.Scanner;

public class AccountCreateProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }

    public AccountCreateProcessor(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void processOperation(){
        System.out.println("Enter the user id for which to create an account: ");
        int userId = Integer.parseInt(scanner.nextLine());
        var user = userService.findUserById(userId).orElseThrow(() -> new IllegalArgumentException("No such user"));
        var account =  accountService.createAccount(user);
        user.getAccountList().add(account);
        System.out.println("New account created: " + account.toString() + "\nfor user: " + user.toString());
    }

}
