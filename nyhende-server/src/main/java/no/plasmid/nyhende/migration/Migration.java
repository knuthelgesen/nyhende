package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.orientdb.service.TransactionFactory;

public abstract class Migration {

    protected TransactionFactory transactionFactory;

    public Migration(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public abstract String getDescription();

    public abstract void upgrade();

}
