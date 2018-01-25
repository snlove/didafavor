package sn.didafavor.data;

/**
 * Created by pc on 2018/1/9.
 */

public class CategoryData {
    private  int id;
    private  int resource_id;
    private String title;

    public CategoryData(int id, int resource_id, String title) {
        this.id = id;
        this.resource_id = resource_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
