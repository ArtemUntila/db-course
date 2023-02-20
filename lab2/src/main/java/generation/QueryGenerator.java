package generation;

import util.table.*;
import util.type.AuthorType;

public class QueryGenerator {

    private final boolean debug;

    public QueryGenerator(boolean debug) {
        this.debug = debug;
    }

    public String insertGenre(Genre genre) {
        return insertQuery("genre", "title", stringToText(genre.toString()));
    }

    public String insertAuthor(Author author) {
        String table = "author";
        String attributes = "title, country";
        String values = String.format(
                "%s, %s",
                stringToText(author.title), stringToText(author.country)
        );

        return insertQuery(table, attributes, values);
    }

    public String insertAlbum(Album album) {
        String table = "album";
        String attributes = "type, title, release, author_id";
        String values = String.format(
                "%s, %s, %s, %d",
                stringToText(album.type.name()), stringToText(album.title), stringToText(album.release), album.author_id
        );

        return insertQuery(table, attributes, values);
    }

    public String insertTrack(Track track) {
        String table = "track";
        String attributes = "title, album_id, duration, lyrics";
        String values = String.format(
                "%s, %d, %s, %s",
                stringToText(track.title), track.album_id, stringToText(track.duration), stringToText(track.lyrics)
        );

        return insertQuery(table, attributes, values);
    }

    public String insertAuthorGenre(int author_id, int genre_id) {
        String table = "author_genre";
        String attribute = "author_id, genre_id";
        String values = String.format("%d, %d", author_id, genre_id);

        return insertQuery(table, attribute, values);
    }

    public String insertAlbumGenre(int album_id, int genre_id) {
        String table = "album_genre";
        String attribute = "album_id, genre_id";
        String values = String.format("%d, %d", album_id, genre_id);

        return insertQuery(table, attribute, values);
    }

    public String insertAuthorTrack(int author_id, int track_id, AuthorType type) {
        String table = "author_track";
        String attribute = "author_id, track_id, type";
        String values = String.format("%d, %d, %s", author_id, track_id, stringToText(type.name()));

        return insertQuery(table, attribute, values);
    }

    public String insertUser(User user) {
        String table = "\"user\"";
        String attribute = "\"name\"";
        String values = stringToText(user.name);

        return insertQuery(table, attribute, values);
    }

    public String insertPlaylist(Playlist playlist) {
        String table = "playlist";
        String attribute = "title, user_id";
        String values = String.format("%s, %d", stringToText(playlist.title), playlist.user_id);

        return insertQuery(table, attribute, values);
    }

    public String insertPlaylistTrack(int playlist_id, int track_id) {
        String table = "playlist_track";
        String attribute = "playlist_id, track_id";
        String values = String.format("%d, %d", playlist_id, track_id);

        return insertQuery(table, attribute, values);
    }

    private String insertQuery(String table, String attributes, String values) {
        String query = String.format("INSERT INTO %s (%s) VALUES (%s) ON CONFLICT DO NOTHING;", table, attributes, values);
        if (debug) System.out.println(query);
        return query;
    }

    public String selectMaxIDQuery(String table) {
        String query = String.format("SELECT MAX(%s_id) FROM \"%s\";", table, table);
        if (debug) System.out.println(query);
        return query;
    }

    private String stringToText(String s) {
        if (s == null) return null;
        else return String.format("'%s'", s);
    }
}
