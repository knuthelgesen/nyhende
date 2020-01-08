package no.plasmid.nyhende;

import no.plasmid.nyhende.config.NyhendeConfig;
import no.plasmid.nyhende.testutil.JUnitContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("junit")
@ContextConfiguration(classes= JUnitContextConfig.class,initializers = ConfigFileApplicationContextInitializer.class,loader= AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
@EnableConfigurationProperties(NyhendeConfig.class)
public class NyhendeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
