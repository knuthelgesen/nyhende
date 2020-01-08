package no.plasmid.nyhende.json;

public class ArticleJson {

    private String id;
    private String name;
    private String parentId;
    private String lead;
    private String body;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getParentId() { return parentId; }
    public String getLead() { return lead; }
    public String getBody() { return body; }

    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setParentId(String parentId) { this.parentId = parentId; }
    public void setLead(String lead) { this.lead = lead; }
    public void setBody(String body) { this.body = body; }
}
