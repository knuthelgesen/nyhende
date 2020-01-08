package no.plasmid.nyhende.service;

import no.plasmid.nyhende.json.ArticleJson;

public interface ArticleService {

    ArticleJson get(String articleId);

    ArticleJson getTemplate(String parentId);

    ArticleJson create(ArticleJson articleJson);

    ArticleJson update(ArticleJson articleJson);

}
