package com.vpolosov.trainee.mergexml.utils;

import org.jgrapht.Graph;

/**
 * Вспомогательный класс для {@link Graph}.
 * <p>
 * Добавляет возможность добавлять массивы вершин при добавлении и создании рёбер.
 *
 * @author Maksim Litvinenko
 */
public class GraphUtil {

    /**
     * Добавить вершины к графу.
     *
     * @param graph  объект в который будут помещены вершины.
     * @param vertex вершины графа.
     * @param <V>    тип вершины графа
     * @param <E>    тип ребра графа
     */
    @SafeVarargs
    public static <V, E> void addVertex(Graph<V, E> graph, V... vertex) {
        for (var v : vertex) {
            graph.addVertex(v);
        }
    }

    /**
     * Добавить к основной вершине рёбра до целевых вершин.
     *
     * @param graph        объект в котором будут установлены рёбра.
     * @param sourceVertex основная вершина.
     * @param targetVertex целевые вершины.
     * @param <V>          тип вершины графа
     * @param <E>          тип ребра графа
     */
    @SafeVarargs
    public static <V, E> void addEdge(Graph<V, E> graph, V sourceVertex, V... targetVertex) {
        for (var e : targetVertex) {
            graph.addEdge(sourceVertex, e);
        }
    }

    /**
     * Добавить к каждой основной вершине рёбра до целевых вершин.
     *
     * @param graph        объект в котором будут установлены рёбра.
     * @param sourceVertex основные вершины.
     * @param targetVertex целевые вершины.
     * @param <V>          тип вершины графа
     * @param <E>          тип ребра графа
     */
    public static <V, E> void addEdge(Graph<V, E> graph, V[] sourceVertex, V[] targetVertex) {
        for (var v1 : sourceVertex) {
            for (var v2 : targetVertex) {
                graph.addEdge(v1, v2);
            }
        }
    }

    /**
     * Добавить к целевой вершине рёбра до основных вершин.
     *
     * @param graph        объект в котором будут установлены рёбра.
     * @param targetVertex целевая вершина.
     * @param sourceVertex основные вершины.
     * @param <V>          тип вершины графа
     * @param <E>          тип ребра графа
     */
    @SafeVarargs
    public static <V, E> void addEdgeRevers(Graph<V, E> graph, V targetVertex, V... sourceVertex) {
        for (var e : sourceVertex) {
            graph.addEdge(e, targetVertex);
        }
    }
}
