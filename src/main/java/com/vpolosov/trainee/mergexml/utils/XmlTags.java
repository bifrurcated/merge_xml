package com.vpolosov.trainee.mergexml.utils;

import lombok.experimental.UtilityClass;

/**
 * Вспомогательный гласс в котором находятся константы тегов для {@code XMl} документа.
 *
 * @author Maksim Litvinenko
 */
@UtilityClass
public class XmlTags {

    /**
     * Название основного элемента XML документа.
     */
    public static final String BS_MESSAGE = "BSMessage";

    /**
     * Информация о клиенте.
     */
    public static final String BS_HEAD = "BSHead";

    /**
     * Уникальный генерируемый идентификатор итогового документа.
     */
    public static final String ID = "ID";

    /**
     * Дата и время проведения парсинга.
     */
    public static final String DATE_TIME = "DateTime";

    /**
     * Основная информация о платежах.
     */
    public static final String DOCUMENTS = "DOCUMENTS";

    /**
     * Основная информация о платеже.
     */
    public static final String DOCUMENT = "DOCUMENT";

    /**
     * Ref Документа.
     */
    public static final String DOCREF = "DOCREF";

    /**
     * Сумма платежа.
     */
    public static final String AMOUNT = "AMOUNT";

    /**
     * Дата создания документа.
     */
    public static final String DOCUMENTDATE = "DOCUMENTDATE";

    /**
     * Плательщик.
     */
    public static final String PAYER = "PAYER";

    /**
     * Код валюты.
     */
    public static final String CURRCODE = "CURRCODE";

    /**
     * IP адрес.
     */
    public static final String IP = "IP";

    /**
     * Код программ доходов бюджетов.
     */
    public static final String CODEREV = "CODEREV";

    /**
     * Основание платежа.
     */
    public static final String PAYGRNDPARAM = "PAYGRNDPARAM";

    /**
     * Тип платежа.
     */
    public static final String PAYTYPEPARAM = "PAYTYPEPARAM";
}
