package util.table;

public class Playlist {
    private static int playlist_id;

    public static int getID() {
        return playlist_id;
    }

    public static void setID(int id) {
        playlist_id = id;
    }

    public String title;
    public int user_id;

    public Playlist(String title, int user_id) {
        playlist_id++;

        this.title = title;
        this.user_id = user_id;
    }
}
