package no.plasmid.nyhende.orientdb;

import com.orientechnologies.orient.core.metadata.sequence.OSequence;
import com.orientechnologies.orient.core.metadata.sequence.OSequenceLibrary;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.*;
import no.plasmid.nyhende.domain.domainobject.DomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class OrientDBTransactionlessWrapper extends AbstractOrientDBWrapper {

    private final static Logger LOG = LoggerFactory.getLogger(OrientDBTransactionlessWrapper.class);

    private static OrientDBTransactionlessWrapper instance;

    private OrientGraphNoTx graph;

    public OrientDBTransactionlessWrapper(OrientGraphNoTx graph) {
        pushInstance(this);
        this.graph = graph;
    }

    private static void pushInstance(OrientDBTransactionlessWrapper transaction) {
        instance = transaction;
    }

    public static OrientDBTransactionlessWrapper getInstance() {
        return instance;
    }

    private static void closeInstance() {
        instance = null;
    }

    public void finish() {
        if (!graph.isClosed()) {
            closeInstance();
            LOG.debug("Commit transaction");

            graph.commit();
//            graph.
//            graph.shutdown();
        }
    }

    protected OrientBaseGraph getGraph() {
        return graph;
    }

    public void createSequence(String name) {
        OSequenceLibrary sequenceLib = graph.getRawGraph().getMetadata().getSequenceLibrary();
        sequenceLib.createSequence(name, OSequence.SEQUENCE_TYPE.ORDERED, new OSequence.CreateParams().setStart(1L).setIncrement(1));
    }

    public <T extends DomainObject<?>> Iterable<T> findVertexInstances(Class<T> vertexClass, String propertyName, String propertyValue) {
        LOG.debug("Get vertices with java class " + vertexClass.getSimpleName() + " and property " + propertyName + " equals " + propertyValue);
        return wrapIterable(vertexClass, graph.getVertices("V." + propertyName, propertyValue));
    }

    private <T extends DomainObject<?>> Iterable<T> wrapIterable(Class<T> theClass, final Iterable<Vertex> graphIterable) {
        return () -> {
            final Iterator<Vertex> iterator = graphIterable.iterator();

            return new Iterator<T>() {

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public T next() {
                    try {
                        return theClass.getConstructor(OrientVertex.class).newInstance((OrientVertex)iterator.next());
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                        throw new RuntimeException("Error processing query result!", e);
                    }
                }
            };
        };
    }

}
