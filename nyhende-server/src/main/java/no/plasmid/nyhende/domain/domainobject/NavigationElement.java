package no.plasmid.nyhende.domain.domainobject;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.domainrelation.ParentChild;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public abstract class NavigationElement<T extends NavigationElement> extends DomainObject<NavigationElement<T>> {

    private static final String PROPERTY_PAGE_TEMPLATE = "pageTemplate";

    public NavigationElement(OrientVertex ov) {
        super(ov);
    }

    public NavigationElement(String name, String pageTemplate) {
        super(name);
        setPageTemplate(pageTemplate);
    }

    public String getPageTemplate() {
        return getProperty(PROPERTY_PAGE_TEMPLATE);
    }

    public T setPageTemplate(String pageTemplate) {
        setProperty(PROPERTY_PAGE_TEMPLATE, pageTemplate);
        return (T)this;
    }

    @SuppressWarnings({"unchecked"})
    public <R extends NavigationElement<?>> R getParent() {
        return getRelatedDomainObject(ParentChild.class, Direction.IN);
    }

    @SuppressWarnings({"unchecked"})
    public T setParent(NavigationElement parent, String urlFragment) {
        new ParentChild(parent, this, urlFragment);
        return (T)this;
    }

    @SuppressWarnings("unchecked")
    public <R extends NavigationElement<?>> List<R> getChildren() {
        return getRelatedDomainObjects(ParentChild.class, Direction.OUT);
    }

    @SuppressWarnings({"unchecked"})
    public <R extends NavigationElement<?>> R findChild(String urlFragment) {
        return (R)getRelatedDomainObject(ParentChild.class, Direction.OUT, new EdgeProperty(ParentChild.PROPERTY_URL_FRAGMENT, urlFragment));
    }

    @SuppressWarnings({"unchecked"})
    public T addChild(NavigationElement child, String urlFragment) {
        child.setParent(this, urlFragment);
        return (T)this;
    }

    @SuppressWarnings({"unchecked"})
    public <R extends NavigationElement<?>> R findDecendent(String url) {
        NavigationElement<R> rc = (NavigationElement<R>) this;
        String[] urlParts = url.split("/");
        for (String urlPart : urlParts) {
            if (StringUtils.isEmpty(urlPart)) {
                continue;
            }
            NavigationElement candidate = rc.findChild(urlPart);
            if (null == candidate) {
                if ("new-article".equals(urlPart)) {
                    return (R) rc;
                } else {
                    return null;
                }
            } else {
                rc = candidate;
            }
        }

        return (R) rc;
    }

    public String getUrlString() {
        ParentChild relationToParent = getDomainRelation(ParentChild.class, Direction.IN);
        if (null == relationToParent) {
            return "";
        } else {
            return relationToParent.getParentDomainObject().getUrlString() + "/" + relationToParent.getUrlFragment();
        }
    }

}
