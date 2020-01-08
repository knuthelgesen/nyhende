package no.plasmid.nyhende.orientdb.service.impl;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.OrientDBTransactionlessWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!junit")
public class OrientDBTransactionFactory implements TransactionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(OrientDBTransactionFactory.class);

    protected static OrientGraphFactory factory;

    @Override
    public void connect() {
        LOG.debug("Create factory");

        if (null == factory) {
            factory = new OrientGraphFactory("plocal:./databases/test").setupPool(1, 10);
        }
    }

    @Override
    public void disconnect() {
        LOG.debug("Close factory");

        if (null != factory) {
            factory.close();
        }
    }

    @Override
    public OrientDBTransactionWrapper getTransaction() {
        LOG.debug("Get transaction");

        if (null == factory) { throw new IllegalStateException("Factory not initialized"); }
        return new OrientDBTransactionWrapper(factory.getTx());
    }

    @Override
    public OrientDBTransactionlessWrapper getTransactionless() {
        LOG.debug("Get transactionless");

        if (null == factory) { throw new IllegalStateException("Factory not initialized"); }
        return new OrientDBTransactionlessWrapper(factory.getNoTx());
    }

}