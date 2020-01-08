package no.plasmid.nyhende.migration;

import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.domainobject.DomainObject;

public class FinishedMigration extends DomainObject<FinishedMigration> {

    public FinishedMigration(OrientVertex ov) { super(ov); }

    public FinishedMigration(String name) {
        super(name);
    }

}
