package no.plasmid.nyhende.domain.domainobject;

import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;

public class NavigationPage extends NavigationElement<NavigationPage> {

    public static final String URN_FRONT_PAGE = "front-page";

    public NavigationPage(OrientVertex ov) {
        super(ov);
    }

    public NavigationPage(String name, String pageTemplate) {
        super(name, pageTemplate);
    }

    public static NavigationPage getFrontPage() {
        return OrientDBTransactionWrapper.getInstance().getByURN(URN_FRONT_PAGE);
    }

}
