package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.aspect.Loggable;
import com.vpolosov.trainee.mergexml.config.GraphConfig;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyCoderevNotFoundException;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyPayGrndParamNotFoundException;
import com.vpolosov.trainee.mergexml.handler.exception.NoSingleDependencyPayInfoException;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyPayTypeParamNotFoundException;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import com.vpolosov.trainee.mergexml.utils.Vertex;
import lombok.RequiredArgsConstructor;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.util.function.Predicate;

import static com.vpolosov.trainee.mergexml.utils.XmlTags.CODEREV;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.PAYGRNDPARAM;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.PAYTYPEPARAM;

/**
 * Проверка на соответствие кода программы доходов бюджетов, типа платежа и
 * основания платежа на связь, которая определена в {@link GraphConfig#graph}.
 *
 * @author Maksim Litvinenko
 */
@Component
@RequiredArgsConstructor
public class PayInfoValidator implements Predicate<Document> {

    /**
     * Вспомогательный класс для работы с {@link Document}.
     */
    private final DocumentUtil documentUtil;

    /**
     * Граф зависимостей.
     */
    private final Graph<Vertex, DefaultEdge> graph;

    @Override
    @Loggable
    public boolean test(Document document) {
        var coderev = documentUtil.getValueByTagName(document, CODEREV);
        var payTypeParam = documentUtil.getValueByTagName(document, PAYTYPEPARAM);
        var payGrndParam = documentUtil.getValueByTagName(document, PAYGRNDPARAM);
        var vCodeRev = new Vertex(coderev, CODEREV);
        var vPayTypeParam = new Vertex(payTypeParam, PAYTYPEPARAM);
        var vPayGrndParam = new Vertex(payGrndParam, PAYGRNDPARAM);
        if (!graph.containsVertex(vCodeRev)) {
            throw new DependencyCoderevNotFoundException(
                "Код программы доходов бюджетов не найден в графе зависимостей"
            );
        }
        if (!graph.containsVertex(vPayTypeParam)) {
            throw new DependencyPayTypeParamNotFoundException(
                "Тип платежа не найден в графе зависимостей"
            );
        }
        if (!graph.containsVertex(vPayGrndParam)) {
            throw new DependencyPayGrndParamNotFoundException(
                "Основание платежа не найдено в графе зависимостей"
            );
        }
        if (!(graph.containsEdge(vCodeRev, vPayTypeParam) && graph.containsEdge(vPayTypeParam, vPayGrndParam))) {
            throw new NoSingleDependencyPayInfoException(
                "Код программы доходов бюджетов, тип платежа и основание платежа не имеют единой связи"
            );
        }
        return true;
    }
}
