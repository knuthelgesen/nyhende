package no.plasmid.nyhende.service.impl;

import no.plasmid.nyhende.domain.domainobject.NavigationElement;
import no.plasmid.nyhende.domain.domainobject.NavigationPage;
import no.plasmid.nyhende.json.MenuItemJson;
import no.plasmid.nyhende.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final static Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Override
    public List<MenuItemJson> getMenu() {
        List<MenuItemJson> rc = new ArrayList<>();
//        for (NavigationElement child : NavigationPage.getFrontPage().getChildren()) {
//            rc.add(createMenuItem(child));
//        }
        rc.add(createMenuItem(NavigationPage.getFrontPage()));
        return rc;
    }

    private MenuItemJson createMenuItem(NavigationElement navigationElement) {
        MenuItemJson menuItemJson = new MenuItemJson();
        menuItemJson.setId(navigationElement.getId());
        menuItemJson.setName(navigationElement.getName());
        menuItemJson.setUrl(navigationElement.getUrlString());

        List<NavigationElement> children = navigationElement.getChildren();
        for (NavigationElement child : children) {
            menuItemJson.getChildren().add(createMenuItem(child));
        }
        return menuItemJson;
    }

}
