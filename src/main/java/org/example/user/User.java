package org.example.user;

import org.example.account.Account;

import java.util.List;

public class User {
    private final int id;
    private final String login;
    private final List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public User(int id, String login, List<Account> accountList) {
        this.id = id;
        this.login = login;
        this.accountList = accountList;
    }
}
