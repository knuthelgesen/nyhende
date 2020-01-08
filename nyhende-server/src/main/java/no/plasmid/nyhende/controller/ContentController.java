package no.plasmid.nyhende.controller;

import no.plasmid.nyhende.domain.domainobject.NavigationElement;
import no.plasmid.nyhende.domain.domainobject.NavigationPage;
import no.plasmid.nyhende.exception.NotFoundException;
import no.plasmid.nyhende.json.ContentTypeJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/content")
public class ContentController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(produces = "application/json")
    public ContentTypeJson getContentType(@PathParam(value = "url") String url) {
        LOG.debug("Find content type for navigation element with URL: {}", url);
        NavigationElement<?> found = NavigationPage.getFrontPage().findDecendent(url);
        if (null == found) { throw new NotFoundException("Could not find content with URL: " + url); }

        ContentTypeJson contentTypeJson = new ContentTypeJson();
        contentTypeJson.setId(found.getId());
        contentTypeJson.setName(found.getName());
        contentTypeJson.setContentType("frontPage");
        return contentTypeJson;
    }

}
