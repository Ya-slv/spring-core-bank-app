package org.example.account;

import org.example.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountService {
    public AccountService() {
        accountMap = new HashMap<>();
        idCounter = 0;
    }

    private final Map<Integer,Account> accountMap;
    private int idCounter;

    public Account createAccount(User user){
        idCounter++;

        Account newAccount = new Account(0,idCounter, user.getId()); // todo default amount
        accountMap.put(newAccount.getId(), newAccount);
        return newAccount;


    }
    public Optional<Account> findAccountById(int id){
        return Optional.ofNullable(accountMap.get(id));

    }
    public List<Account> getAllUserAccounts(int userId){
        return accountMap.values().stream().filter(account -> account.getUserId() == userId).toList();
    }
    public void depositeAccount(int accountId, int moneyToDeposite){
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account no such"));
        if (moneyToDeposite <= 0) {
            throw new IllegalArgumentException("cannot deposit not positive amount");
        }
        account.setMoneyAmount(account.getMoneyAmount()+moneyToDeposite);
    }
}
