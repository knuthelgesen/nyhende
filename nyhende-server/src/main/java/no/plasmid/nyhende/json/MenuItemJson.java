package no.plasmid.nyhende.json;

import java.util.ArrayList;
import java.util.List;

public class MenuItemJson {

    private String id;
    private String name;
    private String url;
    private List<MenuItemJson> children;

    public MenuItemJson() {
        this.children = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUrl() { return url; }
    public List<MenuItemJson> getChildren() {
        return children;
    }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUrl(String url) { this.url = url; }
    public void setChildren(List<MenuItemJson> children) {
        this.children = children;
    }

}
