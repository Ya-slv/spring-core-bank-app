package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        OperationConsoleListener consoleListener =  context.getBean(OperationConsoleListener.class);
        consoleListener.start();
        consoleListener.listen();
        consoleListener.endListen();

    }
}
