package no.plasmid.nyhende.domain.domainobject;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.DomainUtils;
import no.plasmid.nyhende.domain.domainrelation.DomainRelation;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainObject<T extends DomainObject<T>> {

    public static final String PROPERTY_CLASS_NAME = "className";
    public static final String PROPERTY_ID         = "vId";
    public static final String PROPERTY_NAME       = "name";
    public static final String PROPERTY_URN        = "urn";

    private final OrientVertex ov;

    public DomainObject(OrientVertex ov) {
        this.ov = ov;
    }

    public DomainObject(String name) {
        ov = OrientDBTransactionWrapper.getInstance().createVertex(this.getClass(), name);
    }

    public OrientVertex getOrientVertex() {
        return ov;
    }

    public String getId() {
        return getProperty(PROPERTY_ID);
    }

    public String getName() {
        return getProperty(PROPERTY_NAME);
    }

    @SuppressWarnings("unchecked")
    public T setName(String name) {
        setProperty(PROPERTY_NAME, name);
        return (T)this;
    }

    public String getUrn() {
        return getProperty(PROPERTY_URN);
    }

    @SuppressWarnings("unchecked")
    public <R extends DomainObject<?>> R setUrn(String urn) {
        setProperty(PROPERTY_URN, urn);
        return (R)this;
    }

    protected <R> R getProperty(String propertyName) {
        return ov.getProperty(propertyName);
    }

    protected <R> void setProperty(String propertyName, R value) {
        ov.setProperty(propertyName, value);
    }

    @SuppressWarnings("unchecked")
    protected <R extends DomainObject<?>> R getRelatedDomainObject(Class<? extends DomainRelation<?>> relationType, Direction direction, EdgeProperty... edgeProperties) {
        R rc = null;
        VertexQuery query = ov.query().direction(direction).has(PROPERTY_CLASS_NAME, relationType.getName());
        for (EdgeProperty edgeProperty : edgeProperties) {
            query.has(edgeProperty.propertyName, edgeProperty.propertyValue);
        }
        for (Vertex vertex : query.vertices()) {
            rc = DomainUtils.createDomainObjectInstance((OrientVertex) vertex);
            break;
        }
        return rc;
    }

    @SuppressWarnings("unchecked")
    protected <R extends DomainObject<?>> List<R> getRelatedDomainObjects(Class<? extends DomainRelation<?>> relationType, Direction direction, EdgeProperty... edgeProperties) {
        List<R> rc = new ArrayList<>();
        VertexQuery query = ov.query().direction(direction).has(PROPERTY_CLASS_NAME, relationType.getName());
        for (EdgeProperty edgeProperty : edgeProperties) {
            query.has(edgeProperty.propertyName, edgeProperty.propertyValue);
        }
        for (Vertex vertex : query.vertices()) {
            rc.add(DomainUtils.createDomainObjectInstance((OrientVertex) vertex));
        }
        return rc;
    }

    @SuppressWarnings("unchecked")
    protected  <R extends DomainRelation<?>> R getDomainRelation(Class<? extends DomainRelation<?>> relationType, Direction direction, EdgeProperty... edgeProperties) {
        R rc = null;
        VertexQuery query = ov.query().direction(direction).has(PROPERTY_CLASS_NAME, relationType.getName());
        for (EdgeProperty edgeProperty : edgeProperties) {
            query.has(edgeProperty.propertyName, edgeProperty.propertyValue);
        }
        for (Edge edge : query.edges()) {
            rc = DomainUtils.createDomainRelationInstance((OrientEdge) edge);
            break;
        }
        return rc;
    }

    protected class EdgeProperty {
        private String propertyName;
        private Object propertyValue;

        protected EdgeProperty(String propertyName, Object propertyValue) {
            this.propertyName = propertyName;
            this.propertyValue = propertyValue;
        }
    }

}
