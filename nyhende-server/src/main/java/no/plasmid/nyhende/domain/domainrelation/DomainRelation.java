package no.plasmid.nyhende.domain.domainrelation;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import no.plasmid.nyhende.domain.DomainUtils;
import no.plasmid.nyhende.domain.domainobject.DomainObject;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;

public class DomainRelation<T extends DomainRelation> {

    public static final String PROPERTY_CLASS_NAME = "className";

    private final OrientEdge oe;

    public DomainRelation(OrientEdge oe) {
        this.oe = oe;
    }

    public DomainRelation(DomainObject<?> fromObject, DomainObject<?> toObject) {
        oe = OrientDBTransactionWrapper.getInstance().createEdge(this.getClass(), fromObject.getOrientVertex(), toObject.getOrientVertex());
    }

    protected <R> R getProperty(String propertyName) {
        return oe.getProperty(propertyName);
    }

    protected <R> void setProperty(String propertyName, R value) {
        oe.setProperty(propertyName, value);
    }

    protected  <R extends DomainObject<?>> R getFromDomainObject() {
        return getDomainObject(Direction.OUT);
    }

    protected  <R extends DomainObject<?>> R getToDomainObject() {
        return getDomainObject(Direction.IN);
    }

    private <R extends DomainObject<?>> R getDomainObject(Direction direction) {
        return DomainUtils.createDomainObjectInstance(oe.getVertex(direction));
    }

}
