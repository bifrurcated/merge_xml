package com.vpolosov.trainee.mergexml.handler.exception;

/**
 * Исключение выбрасываемое при некорректной максимальной сумме.
 *
 * @author Maksim Litvinenko
 */
public class IncorrectMaxAmountException extends RuntimeException {

    /**
     * Конструктор с одним параметром.
     *
     * @param message текст ошибки.
     */
    public IncorrectMaxAmountException(String message) {
        super(message);
    }
}
