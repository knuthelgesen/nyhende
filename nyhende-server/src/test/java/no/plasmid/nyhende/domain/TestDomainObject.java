package no.plasmid.nyhende.domain;

import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.domainobject.DomainObject;

public class TestDomainObject extends DomainObject<TestDomainObject> {

    public TestDomainObject(OrientVertex ov) { super(ov); }

    public TestDomainObject(String name) {
        super(name);
    }

}
