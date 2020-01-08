package no.plasmid.nyhende.domain;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.testutil.InMemoryTestCase;
import no.plasmid.nyhende.testutil.JUnitContextConfig;
import org.junit.Assert;
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
public class DomainObjectTest extends InMemoryTestCase {

    @Test
    public void can_create_domain_object() {
        TestDomainObject tdo = new TestDomainObject("Testobjekt");

        Assert.assertNotNull(tdo);
        Assert.assertNotNull(tdo.getId());
    }

    @Test
    public void can_have_name() {
        TestDomainObject tdo = new TestDomainObject("Testobjekt");
        Assert.assertEquals("Testobjekt", tdo.getName());

        tdo.setName("A name");
        Assert.assertEquals("A name", tdo.getName());
    }

    @Test
    public void can_have_urn() {
        TestDomainObject tdo = new TestDomainObject("Testobjekt");

        tdo.setUrn("urn:testobjektet");
        Assert.assertEquals("urn:testobjektet", tdo.getUrn());
    }

}
