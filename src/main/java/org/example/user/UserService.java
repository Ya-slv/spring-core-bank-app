package org.example.user;

import org.example.account.Account;
import org.example.account.AccountService;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.*;

public class UserService {

    private final Map<Integer, User> userMap;
    private final Set<String> takenLogins;
    private int idCounter;
    private AccountService accountService;
    public UserService(AccountService accountService) {
        userMap = new HashMap<>();
        takenLogins = new HashSet<>();
        idCounter = 0;
        this.accountService = accountService;
    }

    public User createUser(String login){

        if (takenLogins.contains(login)) throw new IllegalArgumentException("User already exist");
        takenLogins.add(login);
        idCounter++;
        User newUser = new User(idCounter, login, new ArrayList<>());

        Account newAccount = accountService.createAccount(newUser);

        newUser.getAccountList().add(newAccount);
        userMap.put(idCounter, newUser);
        return newUser;
    }

    public Optional<User> findUserById(int id){
        return Optional.ofNullable(userMap.get(id));
    }

    public List<User> getAllUsers(){
        return userMap.values().stream().toList();
    }
}
