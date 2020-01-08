package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.orientdb.service.TransactionFactory;

public abstract class ExampleDataMigration extends Migration {

    public ExampleDataMigration(TransactionFactory transactionFactory) {
        super(transactionFactory);
    }

}
