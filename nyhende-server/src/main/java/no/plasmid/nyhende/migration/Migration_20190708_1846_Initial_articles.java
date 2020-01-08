package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.domain.domainobject.Article;
import no.plasmid.nyhende.domain.domainobject.NavigationPage;
import no.plasmid.nyhende.domain.domainrelation.ParentChild;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;

public class Migration_20190708_1846_Initial_articles extends Migration {

    public Migration_20190708_1846_Initial_articles(TransactionFactory transactionFactory) {
        super(transactionFactory);
    }

    @Override
    public String getDescription() {
        return "Create initial articles";
    }

    @Override
    public void upgrade() {
        OrientDBTransactionWrapper transaction = this.transactionFactory.getTransaction();
        Article aboutArticle = new Article("About");
        new ParentChild(NavigationPage.getFrontPage(), aboutArticle, "about");
        Article projectsArticle = new Article("Projects");
        new ParentChild(NavigationPage.getFrontPage(), projectsArticle, "projects");
        Article lhamArticle = new Article("LHAM");
        new ParentChild(projectsArticle, lhamArticle, "lham");
        Article lham2Article = new Article("LHAM2");
        new ParentChild(lhamArticle, lham2Article, "lham2");
        transaction.finish();
    }

}
