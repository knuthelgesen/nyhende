package no.plasmid.nyhende;

import no.plasmid.nyhende.migration.MigrationRunner;
import no.plasmid.nyhende.orientdb.service.impl.OrientDBTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NyhendeApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(NyhendeApplication.class);

    @Autowired
    MigrationRunner migrationRunner;

    public static void main(String[] args) {
        LOG.info("Starting application");

        //Initialize DB factory
        OrientDBTransactionFactory transactionFactory = new OrientDBTransactionFactory();
        transactionFactory.connect();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("Shutdown detected");

                try {
                    transactionFactory.disconnect();
//					OrientDBServerManager.shutdownOrientDBServer();
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        });
        SpringApplication.run(NyhendeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        migrationRunner.upgrade();
    }

}
