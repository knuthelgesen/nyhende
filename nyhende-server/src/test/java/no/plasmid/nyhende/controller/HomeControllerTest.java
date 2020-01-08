package no.plasmid.nyhende.controller;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.testutil.InMemoryTestCase;
import no.plasmid.nyhende.testutil.JUnitContextConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@ActiveProfiles("junit")
@EnableConfigurationProperties(NyhendeConfig.class)
@ContextConfiguration(classes= JUnitContextConfig.class,initializers = ConfigFileApplicationContextInitializer.class,loader= AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class HomeControllerTest extends InMemoryTestCase {

    @Autowired
    HomeController homeController;

    private Model model;

    @Before
    public void setUp() {
        model = new BindingAwareModelMap();
    }

    @Test
    public void can_get_index() {
        String returnValue = homeController.getIndex(model);
        Assert.assertEquals("forward:/index.html", returnValue);
        Assert.assertEquals(0, model.asMap().size());
    }


}
