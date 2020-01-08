package no.plasmid.nyhende.testutil;

import no.plasmid.nyhende.controller.HomeController;
import no.plasmid.nyhende.migration.MigrationRunner;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@Profile("junit")
public class JUnitContextConfig {

    @Bean
    public TransactionFactory getTransactionFactory() {
        return new InMemoryOrientDBTransactionFactory();
    }

    @Bean
    public MigrationRunner getMigrationRunner() {
        return new MigrationRunner();
    }

    @Bean
    public HomeController getHomeController() { return new HomeController(); }

//    @Bean
//    public ArticleService getArticleService() { return new ArticleServiceImpl(); }
//
//    @Bean
//    public MenuService getMenuService() { return new MenuServiceImpl(); }

}
