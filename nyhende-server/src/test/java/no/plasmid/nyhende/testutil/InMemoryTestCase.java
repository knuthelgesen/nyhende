package no.plasmid.nyhende.testutil;

import no.plasmid.nyhende.migration.MigrationRunner;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class InMemoryTestCase {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    protected TransactionFactory transactionFactory;

    @Autowired
    MigrationRunner migrationRunner;

    protected OrientDBTransactionWrapper transaction;

    @Before
    public void before() {
        transactionFactory.connect();
        migrationRunner.upgrade();
        transaction = transactionFactory.getTransaction();
    }

    @After
    public void after() {
        transaction.rollback();
        transactionFactory.disconnect();
    }

}
