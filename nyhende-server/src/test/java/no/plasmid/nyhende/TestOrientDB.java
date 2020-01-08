package no.plasmid.nyhende;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.migration.FinishedMigration;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.OrientDBTransactionlessWrapper;
import no.plasmid.nyhende.testutil.InMemoryTestCase;
import no.plasmid.nyhende.testutil.JUnitContextConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("junit")
@EnableConfigurationProperties(NyhendeConfig.class)
@ContextConfiguration(classes= JUnitContextConfig.class,initializers = ConfigFileApplicationContextInitializer.class,loader= AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class TestOrientDB extends InMemoryTestCase {

    @Test
    public void can_connect() {
        OrientDBTransactionWrapper.getInstance();
    }

    @Test
    public void can_run_migrations() {
        OrientDBTransactionlessWrapper transactionless = transactionFactory.getTransactionless();
        Assert.assertTrue(OrientDBTransactionlessWrapper.getInstance().findVertexInstances(FinishedMigration.class, "name", "InitialMigration").iterator().hasNext());
        transactionless.finish();
    }

}
