package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.aspect.Loggable;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectXmlFileException;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.function.BiPredicate;

/**
 * Валидатор XML документа по XSD схеме.
 *
 * @author Ali Takushinov
 * @author Andrei Stalybka
 * @author Maksim Litvinenko
 */
@Component
@RequiredArgsConstructor
public class XmlValidator implements BiPredicate<Document, Validator> {

    /**
     * Логирование для пользователя.
     */
    private final Logger loggerForUser;

    /**
     * Вспомогательный класс для работы с {@link Document}.
     */
    private final DocumentUtil documentUtil;

    /**
     * {@inheritDoc}
     *
     * @throws IncorrectXmlFileException если файл не прошёл проверку XSD схемы.
     */
    @Loggable
    @Override
    public boolean test(Document document, Validator validator) {
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException e) {
            var fileName = documentUtil.getFileName(document);
            loggerForUser.error("Файл {} не прошел проверку.", fileName);
            throw new IncorrectXmlFileException("Invalid XML file with name: " + fileName);
        }
        return true;
    }
}