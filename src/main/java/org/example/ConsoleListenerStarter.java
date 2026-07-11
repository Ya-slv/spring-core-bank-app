package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ConsoleListenerStarter {

    private final OperationConsoleListener operationConsoleListener;

    private Thread consoleListenerThread;

    public ConsoleListenerStarter(OperationConsoleListener operationConsoleListener) {
        this.operationConsoleListener = operationConsoleListener;
    }

    @PostConstruct
    public void postConstruct() {
        this.consoleListenerThread = new Thread(() -> {
            operationConsoleListener.start();
            operationConsoleListener.listen();
        });
        consoleListenerThread.start();
    }

    @PreDestroy
    public void preDestroy(){
        consoleListenerThread.interrupt();
        System.out.println("console listener end");
    }
}
