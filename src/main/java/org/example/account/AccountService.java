package org.example.account;

import org.example.user.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class AccountService {
    public AccountService(AccountProperties accountProperties) {
        accountMap = new HashMap<>();
        idCounter = 0;
        this.accountProperties = accountProperties;
    }

    private final Map<Integer,Account> accountMap;
    private int idCounter;
    private final AccountProperties accountProperties;

    public Account createAccount(User user){
        idCounter++;

        Account newAccount = new Account(accountProperties.getDefaultAccountAmount(),idCounter, user.getId());
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
    public void withdrawFromAccount(int accountId, int amountToWithdraw){
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account no such"));
        if (amountToWithdraw <= 0) {
            throw new IllegalArgumentException("cannot withdraw not positive amount");
        }

        if (account.getMoneyAmount() < amountToWithdraw) {
            throw new IllegalArgumentException("Cannot withdraw from account: id=%s, moneyAmount=%s, attemptedWithdraw"
                    .formatted(accountId, account.getMoneyAmount()));
        }
        account.setMoneyAmount(account.getMoneyAmount()-amountToWithdraw);
    }
    public Account closeAccount(int accountId){
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account no such"));
        List<Account> accountList = getAllUserAccounts(account.getUserId());
        if (accountList.size() == 1) throw new IllegalArgumentException(
                "Cannot close the only one account"
        );
        Account accountToDeposit = accountList.stream().filter(it -> it.getId() != accountId).findFirst().orElseThrow();
        accountToDeposit.setMoneyAmount(accountToDeposit.getMoneyAmount()+account.getMoneyAmount());
        accountMap.remove(accountId);
        return account;
    }

    public void transfer(int fromAccountId, int toAccountId, int amountToTransfer){
        Account accountFrom = findAccountById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account no such"));
        Account accountTo = findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account no such"));
        if (amountToTransfer <= 0) {
            throw new IllegalArgumentException("cannot  transfer not positive amount");
        }
        if (accountFrom.getMoneyAmount() < amountToTransfer) {
            throw new IllegalArgumentException("Cannot transfer from account: id=%s, moneyAmount=%s, attemptedWithdraw"
                    .formatted(accountFrom, accountFrom.getMoneyAmount()));
        }

        int totalAmountToDeposit = accountTo.getUserId() != accountFrom.getUserId()
                ? (int) (amountToTransfer - amountToTransfer * accountProperties.getTransferCommission())
                : amountToTransfer;

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-amountToTransfer);
        accountTo.setMoneyAmount(accountTo.getMoneyAmount()+totalAmountToDeposit);

        System.out.println("Transfer successfully");
    }
}
