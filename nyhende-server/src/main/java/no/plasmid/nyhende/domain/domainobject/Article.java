package no.plasmid.nyhende.domain.domainobject;

import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.json.ArticleJson;

public class Article extends NavigationElement<Article> {

    private static final String PROPERTY_LEAD = "lead";
    private static final String PROPERTY_BODY = "body";

    public Article(OrientVertex ov) {
        super(ov);
    }

    public Article(String name) {
        super(name, "article");
    }

    public String getLead() {
        return getProperty(PROPERTY_LEAD);
    }

    public Article setLead(String lead) {
        setProperty(PROPERTY_LEAD, lead);
        return this;
    }

    public String getBody() {
        return getProperty(PROPERTY_BODY);
    }

    public Article setBody(String body) {
        setProperty(PROPERTY_BODY, body);
        return this;
    }

    public ArticleJson toJson() {
        ArticleJson rc = new ArticleJson();
        rc.setId(getId());
        rc.setName(getName());
        rc.setParentId(this.getParent().getId());
        rc.setLead(getLead());
        rc.setBody(getBody());
        return rc;
    }

}
