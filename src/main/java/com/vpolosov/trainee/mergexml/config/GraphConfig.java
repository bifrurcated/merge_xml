package com.vpolosov.trainee.mergexml.config;

import com.vpolosov.trainee.mergexml.utils.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.vpolosov.trainee.mergexml.utils.GraphUtil.addEdge;
import static com.vpolosov.trainee.mergexml.utils.GraphUtil.addEdgeRevers;
import static com.vpolosov.trainee.mergexml.utils.GraphUtil.addVertex;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.CODEREV;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.PAYGRNDPARAM;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.PAYTYPEPARAM;
import static org.apache.commons.lang3.ArrayUtils.toArray;

/**
 * Конфигурация графа.
 *
 * @author Maksim Litvinenko
 */
@Configuration
public class GraphConfig {

    /**
     * Создаёт граф зависимостей «код программ доходов бюджетов», «тип платежа» и «основания платежа».
     *
     * @return граф зависимостей «код программ доходов бюджетов», «тип платежа» и «основания платежа».
     */
    @Bean
    public Graph<Vertex, DefaultEdge> graph() {
        var directedGraph = new DefaultDirectedGraph<Vertex, DefaultEdge>(DefaultEdge.class);

        //«код программ доходов бюджетов»
        var one = new Vertex("1", CODEREV);
        var two = new Vertex("2", CODEREV);
        var three = new Vertex("3", CODEREV);

        //«тип платежа»
        //уплата налога или сбора;
        var ns = new Vertex("НС", PAYTYPEPARAM);
        //уплата аванса или предоплата (в том числе декадные платежи);
        var av = new Vertex("АВ", PAYTYPEPARAM);
        var pl = new Vertex("ПЛ", PAYTYPEPARAM); //уплата платежа;
        var vz = new Vertex("ВЗ", PAYTYPEPARAM); //уплата взноса;
        var gp = new Vertex("ГП", PAYTYPEPARAM); //уплата пошлины;
        var pe = new Vertex("ПЕ", PAYTYPEPARAM); //уплата пени;
        var pz = new Vertex("ПЦ", PAYTYPEPARAM); //уплата процентов;
        //налоговые санкции, установленные Налоговым кодексом Российской Федерации;
        var sa = new Vertex("СА", PAYTYPEPARAM);
        var ah = new Vertex("АШ", PAYTYPEPARAM); //административные штрафы;
        //иные штрафы, установленные соответствующими законодательными или иными нормативными актами
        var ih = new Vertex("ИШ", PAYTYPEPARAM);

        //«основания платежа»
        //платеж текущего года без нарушения срока (текущий платеж);
        var tp = new Vertex("ТП", PAYGRNDPARAM);
        //добровольное погашение задолженности по истекшим налоговым периодам;
        var zd = new Vertex("ЗД", PAYGRNDPARAM);
        //текущие платежи физических лиц - клиентов банка (владельцев счета), уплачиваемые со своего банковского счета;
        var bf = new Vertex("БФ", PAYGRNDPARAM);
        //требование налогового органа;
        var tr = new Vertex("ТР", PAYGRNDPARAM);
        //погашение задолженности по акту проверки;
        var ap = new Vertex("АП", PAYGRNDPARAM);
        //погашение задолженности по исполнительному документу
        var ar = new Vertex("АР", PAYGRNDPARAM);
        //погашение отсроченной задолженности в связи с введением внешнего управления;
        var vu = new Vertex("ВУ", PAYGRNDPARAM);
        //перечисление в счет погашения задолженности, приостановленной ко взысканию;
        var pr = new Vertex("ПР", PAYGRNDPARAM);
        //погашение рассроченной задолженности в соответствии с графиком рассрочки;
        var rs = new Vertex("РС", PAYGRNDPARAM);
        //погашение отсроченной задолженности;
        var ot = new Vertex("ОТ", PAYGRNDPARAM);
        //погашение реструктурируемой задолженности;
        var rt = new Vertex("РТ", PAYGRNDPARAM);

        addVertex(directedGraph, one, two, three);
        addVertex(directedGraph, ns, av, pl, vz, gp, pe, pz, sa, ah, ih);
        addVertex(directedGraph, tp, zd, bf, tr, ap, ar, vu, pr, rs, ot, rt);

        //Связи с первым кодом
        addEdge(directedGraph, one,
            ns, av, pl, vz, gp);
        addEdge(directedGraph, ns,
            tp, zd, tr, ap, ar, ot, rs, pr, vu, rt);
        addEdgeRevers(directedGraph, tp,
            av, pl, vz, gp);

        //Связи со втором кодом
        addEdge(directedGraph, two,
            pe, pz);
        addEdge(directedGraph, pe,
            zd, tr, ot, rs, rt, vu, pr, ap, ar);
        addEdge(directedGraph, pz,
            rs, ot, rt, pr, vu);

        //Связи с третьим кодом
        addEdge(directedGraph, three,
            sa, ah, ih);
        addEdge(directedGraph, toArray(sa, ah, ih), toArray(zd, tr, rs, rt, vu, pr, ap, ar));

        return directedGraph;
    }
}
