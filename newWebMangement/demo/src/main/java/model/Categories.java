package model;

public class Categories extends AbstractModel<Categories>{
    private int id;
    private String title;

    public Categories() {
    }

    public Categories(int cat_id, String title) {
        this.id = cat_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
