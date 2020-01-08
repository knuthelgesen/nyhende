package no.plasmid.nyhende.domain;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.domain.domainrelation.DomainRelation;
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
public class DomainRelationTest extends InMemoryTestCase {

    class TestDomainRelation extends DomainRelation<TestDomainRelation> {

        public TestDomainRelation(TestDomainObject object1, TestDomainObject object2) {
            super(object1, object2);
        }

        public TestDomainObject getFrom() {
            return getFromDomainObject();
        }

        public TestDomainObject getTo() {
            return getToDomainObject();
        }

    }

    @Test
    public void can_create_domain_relation() {
        TestDomainObject tdo1 = new TestDomainObject("Testobject1");
        TestDomainObject tdo2 = new TestDomainObject("Testobject2");

        TestDomainRelation tdr = new TestDomainRelation(tdo1, tdo2);

        Assert.assertNotNull(tdr);
    }

    @Test
    public void can_get_vertices() {
        TestDomainObject tdo1 = new TestDomainObject("Testobject1");
        TestDomainObject tdo2 = new TestDomainObject("Testobject2");

        TestDomainRelation tdr = new TestDomainRelation(tdo1, tdo2);

        Assert.assertNotNull(tdr.getFrom());
        Assert.assertEquals("Testobject1", tdr.getFrom().getName());

        Assert.assertNotNull(tdr.getFrom());
        Assert.assertEquals("Testobject2", tdr.getTo().getName());
    }

}
