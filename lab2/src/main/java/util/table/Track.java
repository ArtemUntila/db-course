package util.table;

public class Track {
    private static int track_id;

    public static int getID() {
        return track_id;
    }

    public static void setID(int id) {
        track_id = id;
    }

    public String title;
    public int album_id;
    public String duration;
    public String lyrics;

    public Track(String title, int album_id, String duration, String lyrics) {
        track_id++;

        this.title = title;
        this.album_id = album_id;
        this.duration = duration;
        this.lyrics = lyrics;
    }
}
