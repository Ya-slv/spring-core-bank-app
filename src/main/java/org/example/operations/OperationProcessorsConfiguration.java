package org.example.operations;


import org.example.account.AccountService;
import org.example.operations.processors.*;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {

    @Bean
    public CreateUserProcessor createUserProcessor(
            Scanner scanner,
            UserService userService
    ) {
        return new CreateUserProcessor(scanner, userService);
    }

    @Bean
    public AccountCreateProcessor accountCreateProcessor(
            Scanner scanner,
            UserService userService,
            AccountService accountService
    ) {
        return new AccountCreateProcessor(scanner,userService,accountService);
    }

    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(
            UserService userService
    ) {
        return new ShowAllUsersProcessor(userService);
    }

    @Bean
    public DepositeAccountProcessor depositeAccountProcessor(
            Scanner scanner,
            AccountService accountService
    ) {
        return new DepositeAccountProcessor(scanner,accountService);
    }

    @Bean
    public AccountWithdrawProcessor accountWithdrawProcessor(
            Scanner scanner,
            AccountService accountService
    ) {
        return new AccountWithdrawProcessor(scanner, accountService);
    }

    @Bean
    public CloseAccountProcessor closeAccountProcessor(
            Scanner scanner,
            AccountService accountService,
            UserService userService
    ) {
        return new CloseAccountProcessor(scanner,accountService,userService);
    }
    @Bean
    public AccountTransferProcessor accountTransferProcessor(
            Scanner scanner,
            AccountService accountService
    ) {
        return new AccountTransferProcessor(scanner,accountService);
    }
}
