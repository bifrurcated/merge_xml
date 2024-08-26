package com.vpolosov.trainee.mergexml.dtos.views;

/**
 * Класс для отображения определенных полей в JSON.
 *
 * @author Samat Hamzin
 */
public class ValidationFileHistoryDtoViews {
    /**
     * Класс для отображения всех полей ДТО внутри кода.
     *
     * @author Samat Hamzin
     */
    public static class Internal {}

    /**
     * Класс для маппинга выходного JSON.
     *
     * @author Samat Hamzin
     */
    public static class Output extends Internal {}

    /**
     * Класс для маппинга входного JSON.
     *
     * @author Samat Hamzin
     */
    public static class Input extends Internal {}
}
