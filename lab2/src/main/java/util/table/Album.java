package util.table;

import util.type.AlbumType;

public class Album {
    private static int album_id;

    public static int getID() {
        return album_id;
    }

    public static void setID(int id) {
        album_id = id;
    }

    public String title;
    public AlbumType type;
    public String release;
    public int author_id;

    public Album(String title, AlbumType type, String release, int author_id) {
        album_id++;

        this.title = title;
        this.type = type;
        this.release = release;
        this.author_id = author_id;
    }
}
