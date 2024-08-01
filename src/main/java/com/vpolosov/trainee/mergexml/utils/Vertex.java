package com.vpolosov.trainee.mergexml.utils;

/**
 * Вершина хранящая информацию о значении и уровне.
 *
 * @author Maksim Litvinenko
 * @param value значение вершины.
 * @param level уровень на котором находится вершина.
 */
public record Vertex(String value, String level) {
}
