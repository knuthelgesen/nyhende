package no.plasmid.nyhende.orientdb.service;

import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.OrientDBTransactionlessWrapper;

public interface TransactionFactory {

    void connect();

    void disconnect();

    OrientDBTransactionWrapper getTransaction();

    OrientDBTransactionlessWrapper getTransactionless();

}
