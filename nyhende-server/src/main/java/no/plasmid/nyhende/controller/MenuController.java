package no.plasmid.nyhende.controller;

import no.plasmid.nyhende.json.MenuItemJson;
import no.plasmid.nyhende.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping(produces = "application/json")
    public List<MenuItemJson> getMenu() {
        LOG.debug("Find menu");

        return menuService.getMenu();
    }


}
