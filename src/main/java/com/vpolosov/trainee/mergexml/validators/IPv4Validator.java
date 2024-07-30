package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.handler.exception.InvalidIPv4Exception;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.vpolosov.trainee.mergexml.utils.XmlTags.IP;

/**
 * Проверка IP адреса на соответствие IPv4.
 *
 * @author Maksim Litvinenko
 */
@Component
@RequiredArgsConstructor
public class IPv4Validator implements Predicate<Document> {

    /**
     * Regexp паттерн для проверки IPv4.
     */
    private static final Pattern IPV4_REGEXP = Pattern.compile(
        "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}"
            + "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    /**
     * Вспомогательный класс для работы с {@link Document}.
     */
    private final DocumentUtil documentUtil;

    /**
     * {@inheritDoc}
     *
     * @throws InvalidIPv4Exception когда IP адрес не соответствует формату IPv4.
     */
    @Override
    public boolean test(Document document) {
        var ipv4 = documentUtil.getValueByTagName(document, IP);
        if (IPV4_REGEXP.matcher(ipv4).matches()) {
            return true;
        }
        throw new InvalidIPv4Exception("IP адрес не соответствует формату IPv4");
    }
}
