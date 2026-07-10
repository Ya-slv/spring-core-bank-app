package org.example;

import org.example.account.AccountService;
import org.example.user.User;
import org.example.user.UserService;

import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final UserService userService;

    private final AccountService accountService;

    public OperationConsoleListener(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }


    public void listen(){
        System.out.println("Type operation");
        while (true){
            String message = scanner.nextLine();
            if (message == "USER_CREATE") System.out.println("user created");
            else System.out.println("Not found");
            System.out.println("Type next operation");
        }
    }
}
