package no.plasmid.nyhende.testutil;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import no.plasmid.nyhende.orientdb.service.impl.OrientDBTransactionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("junit")
public class InMemoryOrientDBTransactionFactory extends OrientDBTransactionFactory {

    @Override
    public void connect() {
        if (null == factory) {
            factory = new OrientGraphFactory("memory:test").setupPool(1, 10);
        }
    }

    @Override
    public void disconnect() {
        if (null != factory) {
            factory.getDatabase().drop();
            factory.getDatabase().close();
            factory.close();
        }
    }

}
