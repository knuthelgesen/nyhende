package no.plasmid.nyhende.orientdb;

import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.DomainUtils;
import no.plasmid.nyhende.domain.domainobject.DomainObject;
import no.plasmid.nyhende.domain.domainrelation.DomainRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class OrientDBTransactionWrapper extends AbstractOrientDBWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(OrientDBTransactionWrapper.class);

    protected static ThreadLocal<Stack<OrientDBTransactionWrapper>> threadBoundInstances = new ThreadLocal<Stack<OrientDBTransactionWrapper>>();

    private OrientGraph graph;

    public OrientDBTransactionWrapper(OrientGraph orientGraph) {
        pushInstance(this);
        this.graph = orientGraph;
    }

    public static OrientDBTransactionWrapper getInstance() {
        Stack<OrientDBTransactionWrapper> transactions = threadBoundInstances.get();
        if (null == transactions) {
            transactions = new Stack<>();
            threadBoundInstances.set(transactions);
        }
        if (transactions.isEmpty()) {
            return null;
        }
        return transactions.peek();
    }

    protected static void pushInstance(OrientDBTransactionWrapper transaction) {
        Stack<OrientDBTransactionWrapper> transactions = threadBoundInstances.get();
        if (null == transactions) {
            transactions = new Stack<>();
            threadBoundInstances.set(transactions);
        }
        transactions.push(transaction);
    }

    protected static void closeInstance() {
        threadBoundInstances.get().pop();
    }

    protected OrientBaseGraph getGraph() {
        return graph;
    }

    public void finish() {
        if (!graph.isClosed()) {
            closeInstance();
            LOG.debug("Commit transaction");

            graph.commit();
            graph.shutdown();
        }
    }

    public void rollback() {
        if (!graph.isClosed()) {
            closeInstance();
            LOG.debug("Rollback transaction");

            graph.rollback();
            graph.shutdown();
        }
    }

    public <T extends DomainObject<?>> T getByURN(String urn) {
        LOG.debug("Get vertex with urn " + urn);
        return DomainUtils.createDomainObjectInstance((OrientVertex)graph.getVertices(DomainObject.PROPERTY_URN, urn).iterator().next());
    }

    public <T extends DomainObject<?>> T getById(String id) {
        LOG.debug("Get vertex with id " + id);
        return DomainUtils.createDomainObjectInstance((OrientVertex)graph.getVertices(DomainObject.PROPERTY_ID, id).iterator().next());
    }

    public OrientEdge createEdge(Class<? extends DomainRelation>theClass, OrientVertex fromVertex, OrientVertex toVertex) {
        LOG.debug("Creting edge with class " + theClass.getName());
        OrientEdge oe = getGraph().addEdge(null, fromVertex, toVertex, "E");
        oe.setProperty(DomainRelation.PROPERTY_CLASS_NAME, theClass.getName());

        return oe;
    }

}
