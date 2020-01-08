package no.plasmid.nyhende.service.impl;

import no.plasmid.nyhende.domain.domainobject.Article;
import no.plasmid.nyhende.json.ArticleJson;
import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.service.ArticleService;
import org.springframework.stereotype.Component;

@Component
public class ArticleServiceImpl implements ArticleService {

    @Override
    public ArticleJson get(String articleId) {
        Article article = OrientDBTransactionWrapper.getInstance().getById(articleId);
        return article.toJson();
    }

    @Override
    public ArticleJson getTemplate(String parentId) {
        ArticleJson rc = new ArticleJson();
        rc.setParentId(parentId);
        return rc;
    }

    @Override
    public ArticleJson create(ArticleJson articleJson) {
        Article article = new Article(articleJson.getName());
        article.setParent(OrientDBTransactionWrapper.getInstance().getById(articleJson.getParentId()), articleJson.getName().toLowerCase().replaceAll(" ", ""));
        article.setLead(articleJson.getLead());
        article.setBody(articleJson.getBody());
        return article.toJson();
    }

    @Override
    public ArticleJson update(ArticleJson articleJson) {
        Article article = OrientDBTransactionWrapper.getInstance().getById(articleJson.getId());
        article.setName(articleJson.getName());
        article.setLead(articleJson.getLead());
        article.setBody(articleJson.getBody());
        return article.toJson();
    }

}
