package no.plasmid.nyhende.domain;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.domain.domainobject.Article;
import no.plasmid.nyhende.testutil.InMemoryTestCase;
import no.plasmid.nyhende.testutil.JUnitContextConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("junit")
@EnableConfigurationProperties(NyhendeConfig.class)
@ContextConfiguration(classes= JUnitContextConfig.class,initializers = ConfigFileApplicationContextInitializer.class,loader= AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class ArticleTest extends InMemoryTestCase {

    Article article;

    @Before
    public void setup() {
        article = new Article("The article");
    }

    @Test
    public void can_create() {
        Assert.assertNotNull(article.getId());
        Assert.assertEquals("The article", article.getName());
    }

    @Test
    public void can_have_lead() {
        article.setLead("Lead text");
        Assert.assertEquals("Lead text", article.getLead());
    }

    @Test
    public void can_have_body() {
        article.setBody("Body text");
        Assert.assertEquals("Body text", article.getBody());
    }

}
