package no.plasmid.nyhende.domain;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.domain.domainobject.NavigationPage;
import no.plasmid.nyhende.domain.domainrelation.ParentChild;
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
public class NavigationPageTest extends InMemoryTestCase {

    @Test
    public void can_create() {
        NavigationPage navPage = new NavigationPage("The page", "test-template");

        Assert.assertEquals("The page", navPage.getName());
    }

    @Test
    public void can_have_page_template() {
        NavigationPage navPage = new NavigationPage("The page", "test-template");

        Assert.assertEquals("test-template", navPage.getPageTemplate());
    }

    @Test
    public void can_create_hierarchy() {
        NavigationPage parent = new NavigationPage("Parent", "test-template");
        NavigationPage child = new NavigationPage("Child", "test-template");
        NavigationPage grandchild = new NavigationPage("Grandchild", "test-template");
        NavigationPage greatGrandchild = new NavigationPage("Great grandchild", "test-template");

        ParentChild parentChild = new ParentChild(parent, child, "generation1");
        grandchild.setParent(child, "generation2");
        grandchild.addChild(greatGrandchild, "generation3");

        Assert.assertEquals(1, parent.getChildren().size());
        Assert.assertEquals("Child", parent.getChildren().get(0).getName());

        Assert.assertEquals(1, child.getChildren().size());
        Assert.assertEquals("Parent", child.getParent().getName());

        Assert.assertEquals(1, grandchild.getChildren().size());

        Assert.assertEquals("Great grandchild", parent.getChildren().get(0).getChildren().get(0).getChildren().get(0).getName());

        Assert.assertEquals("Grandchild", child.findChild("generation2").getName());

        Assert.assertEquals("generation1", parentChild.getUrlFragment());
        Assert.assertEquals("Parent", parentChild.getParentDomainObject().getName());
        Assert.assertEquals("Child", parentChild.getChildDomainObject().getName());
    }

    @Test
    public void can_find_by_url_fragment() {
        NavigationPage parent = new NavigationPage("Parent", "test-template");
        NavigationPage child1 = new NavigationPage("Child 1", "test-template");
        NavigationPage child2 = new NavigationPage("Child 2", "test-template");

        parent.addChild(child1, "child1");
        parent.addChild(child2, "child2");

        Assert.assertEquals(2, parent.getChildren().size());

        Assert.assertEquals("Child 1", parent.findChild("child1").getName());
        Assert.assertEquals("Child 2", parent.findChild("child2").getName());
    }

    @Test
    public void can_generate_url_string() {
        NavigationPage parent = new NavigationPage("Parent", "test-template");
        NavigationPage child = new NavigationPage("Child", "test-template");
        NavigationPage grandchild = new NavigationPage("Grandchild", "test-template");
        NavigationPage greatGrandchild = new NavigationPage("Great grandchild", "test-template");

        parent.addChild(child, "child");
        child.addChild(grandchild, "grandchild");
        grandchild.addChild(greatGrandchild, "greatgrandchild");

        Assert.assertEquals("/child/grandchild/greatgrandchild", greatGrandchild.getUrlString());
    }

    @Test
    public void can_get_front_page() {
        NavigationPage frontpage = NavigationPage.getFrontPage();

        Assert.assertNotNull(frontpage);
        Assert.assertEquals("Front page", frontpage.getName());
    }

}