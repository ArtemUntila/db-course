package util.table;

public class Author {
    private static int author_id;

    public static int getID() {
        return author_id;
    }

    public static void setID(int id) {
        author_id = id;
    }

    public String title;
    public String country;

    public Author(String title, String country) {
        author_id++;

        this.title = title;
        this.country = country;
    }
}
