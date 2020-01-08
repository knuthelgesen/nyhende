package no.plasmid.nyhende.domain.domainrelation;

import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import no.plasmid.nyhende.domain.domainobject.NavigationElement;

public class ParentChild extends DomainRelation<ParentChild> {

    public static final String PROPERTY_URL_FRAGMENT = "urlFragment";

    public ParentChild(OrientEdge oe) {
        super(oe);
    }

    public ParentChild(NavigationElement parentObject, NavigationElement childObject, String urlFragment) {
        super(parentObject, childObject);
        setUrlFragment(urlFragment);
    }

    public String getUrlFragment() {
        return super.getProperty(PROPERTY_URL_FRAGMENT);
    }

    public ParentChild setUrlFragment(String urlFragment) {
        super.setProperty(PROPERTY_URL_FRAGMENT, urlFragment);
        return this;
    }

    public <R extends NavigationElement<?>> R getParentDomainObject() {
        return getFromDomainObject();
    }

    public <R extends NavigationElement<?>> R getChildDomainObject() {
        return getToDomainObject();
    }

}
