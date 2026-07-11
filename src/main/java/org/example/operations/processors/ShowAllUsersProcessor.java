package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.user.User;
import org.example.user.UserService;

import java.util.List;

public class ShowAllUsersProcessor implements OperationCommandProcessor {
    private final UserService userService;

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void processOperation(){
        List<User> arr = userService.getAllUsers();
        System.out.println("List of all users:");
        for (User u:arr) System.out.println(u);
    }
}
