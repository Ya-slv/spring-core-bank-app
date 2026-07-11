package org.example.operations;

public enum ConsoleOperationType {
    // Создание нового пользователя
    USER_CREATE,
    // Отображение списка всех пользователей
    SHOW_ALL_USERS,
    // Создание нового счета для пользователя
    ACCOUNT_CREATE,
    // Закрытие счета
    ACCOUNT_CLOSE,
    // Пополнение счета
    ACCOUNT_DEPOSITE,
    // Перевод средст между счетами
    ACCOUNT_TRANSFER,
    // Снятие средст со счета
    ACCOUNT_WITHDRAW;
}
