package com.vpolosov.trainee.mergexml.handler.exception;

/**
 * Исключение выбрасываемое когда показатели код программы доходов, типа платежа и
 * основание платежа не соответствуют графу зависимостей.
 *
 * @author Maksim Litvinenko
 */
public class NoSingleDependencyPayInfoException extends RuntimeException {

    /**
     * Конструктор с одним параметром.
     *
     * @param message текст ошибки.
     */
    public NoSingleDependencyPayInfoException(String message) {
        super(message);
    }
}
