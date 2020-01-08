package no.plasmid.nyhende.orientdb;

import com.orientechnologies.orient.core.metadata.sequence.OSequence;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.domainobject.DomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractOrientDBWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractOrientDBWrapper.class);

    protected abstract OrientBaseGraph getGraph();

    public OrientVertex createVertex(Class<? extends DomainObject> theClass, String name) {
        OSequence vIdSequence = getGraph().getRawGraph().getMetadata().getSequenceLibrary().getSequence("vIdSeq");

        LOG.debug("Creting vertex with class " + theClass.getName());
        OrientVertex ov = getGraph().addVertex(null, DomainObject.PROPERTY_ID, "V" + vIdSequence.next(), DomainObject.PROPERTY_CLASS_NAME, theClass.getName(), DomainObject.PROPERTY_NAME, name);

        return ov;
    }

}
