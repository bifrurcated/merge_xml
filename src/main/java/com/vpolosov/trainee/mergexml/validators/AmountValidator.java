package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.aspect.Loggable;
import com.vpolosov.trainee.mergexml.config.ConfigProperties;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMaxAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMinAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectValueException;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Predicate;

import static com.vpolosov.trainee.mergexml.utils.XmlTags.AMOUNT;

/**
 * Валидатор минимальной суммы платежа.
 *
 * @author Andrei Stalybka
 */
@Component
@RequiredArgsConstructor
public class AmountValidator implements Predicate<File> {

    /**
     * Свойства приложения.
     */
    private final ConfigProperties configProperties;

    /**
     * Вспомогательный класс для работы с {@link Document}.
     */
    private final DocumentUtil documentUtil;

    /**
     * {@inheritDoc}
     *
     * @throws IncorrectValueException     если в файле не найдена сумма платежа или она некорректна.
     * @throws IncorrectMinAmountException если сумма платежа меньше минимально допустимой.
     * @throws IncorrectMaxAmountException если сумма платежа больше максимально допустимой.
     */
    @Loggable
    @Override
    public boolean test(File xmlFile) {
        var amountStr = documentUtil.getFirstElementByTagName(xmlFile, AMOUNT);
        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
        } catch (Exception e) {
            throw new IncorrectValueException(
                "В файле %s не найдена сумма платежа или сумма некорректна".formatted(xmlFile.getName())
            );
        }
        if (amount.compareTo(configProperties.getMinPayment()) < BigInteger.ZERO.intValue()) {
            throw new IncorrectMinAmountException(
                "В файле %s сумма платежа не соответствует минимальной".formatted(xmlFile.getName())
            );
        }
        if (amount.compareTo(configProperties.getMaxPayment()) > BigInteger.ZERO.intValue()) {
            throw new IncorrectMaxAmountException(
                "В файле %s сумма платежа не соответствует максимальной".formatted(xmlFile.getName())
            );
        }
        return true;
    }
}