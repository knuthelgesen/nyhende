package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.domain.domainobject.NavigationPage;
import no.plasmid.nyhende.domain.domainrelation.ParentChild;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;

public class Migration_20190610_2130_Navigation_Element extends Migration {

    public Migration_20190610_2130_Navigation_Element(TransactionFactory transactionFactory) {
        super(transactionFactory);
    }

    @Override
    public String getDescription() {
        return "Add the class for navigation element and the properties it needs";
    }

    @Override
    public void upgrade() {
        OrientDBTransactionWrapper transaction = this.transactionFactory.getTransaction();
        NavigationPage frontPage = new NavigationPage("Front page", "front-page").setUrn(NavigationPage.URN_FRONT_PAGE);
        NavigationPage searchPage = new NavigationPage("Search", "search");
        new ParentChild(frontPage, searchPage, "search");
        transaction.finish();
    }

}
