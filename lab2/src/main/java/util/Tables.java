package util;

import generation.RandomGenerator;
import util.table.*;
import util.type.AlbumType;

public final class Tables {

    private static final RandomGenerator rg = new RandomGenerator();

    public static Author newAuthor() {
        String title = "author_" + (Author.getID() + 1);
        String country = rg.randomCountry();

        return new Author(title, country);
    }

    public static Album newAlbum(int author_id, AlbumType type) {
        String title = "album_" + (Album.getID() + 1);
        String release = rg.randomDate();

        return new Album(title, type, release, author_id);
    }

    public static Track newTrack(int album_id) {
        String title = "track_" + (Track.getID() + 1);
        String duration = rg.randomTime();
        String lyrics = rg.randomLyrics();

        return new Track(title, album_id, duration, lyrics);
    }

    public static User newUser() {
        String name = String.format("user_%d", User.getID() + 1);

        return new User(name);
    }

    public static Playlist newPlaylist(int user_id) {
        String title = String.format("playlist_%d", Playlist.getID() + 1);

        return new Playlist(title, user_id);
    }
}
