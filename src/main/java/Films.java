package main.java;

public class Films {
    private String title;
    private String overview;
    private int id;


    public Films(int id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return  id + " " + title + " " + overview;
    }


}
