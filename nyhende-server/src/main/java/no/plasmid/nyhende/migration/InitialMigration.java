package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.orientdb.OrientDBTransactionlessWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;

public class InitialMigration extends Migration {

    public InitialMigration(TransactionFactory transactionFactory) {
        super(transactionFactory);
    }

    @Override
    public String getDescription() {
        return "Set up initial classes, properties and sequence in OrientDB";
    }

    @Override
    public void upgrade() {
        //Sequence for vertex id
        OrientDBTransactionlessWrapper.getInstance().createSequence("vIdSeq");
    }

}
