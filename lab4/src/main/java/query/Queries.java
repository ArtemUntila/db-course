package query;

import java.util.Random;

public class Queries {
    public static final Query[] select = {
            new Query(QueryType.SELECT, "author", "SELECT * FROM author;"),
            new Query(QueryType.SELECT, "author", "SELECT * FROM author WHERE country LIKE 'Russia';"),
            new Query(QueryType.SELECT, "album", "SELECT * FROM album;"),
            new Query(QueryType.SELECT, "album", "SELECT * FROM album WHERE type = 'album';"),
            new Query(QueryType.SELECT, "track", "SELECT * FROM track;"),
            new Query(QueryType.SELECT, "track", "SELECT * FROM track WHERE lyrics IS Null;")
    };

    public static final Query[] change = {
            new Query(QueryType.CHANGE, "author", "UPDATE author SET title = 'updated_author' WHERE author_id = (SELECT MAX(author_id) FROM author);"),
            new Query(QueryType.CHANGE, "author", "INSERT INTO author (title, country) VALUES ('new_author', 'Russia');"),
            new Query(QueryType.CHANGE, "album", "UPDATE album SET title = 'updated_album' WHERE album_id = (SELECT MAX(album_id) FROM album);"),
            new Query(QueryType.CHANGE, "album", "INSERT INTO album (type, title, release, author_id) VALUES ('album', 'new_album', '2022-05-01', 1);"),
            new Query(QueryType.CHANGE, "track", "UPDATE track SET title = 'updated_track' WHERE track_id = (SELECT MAX(track_id) FROM track);"),
            new Query(QueryType.CHANGE, "track", "INSERT INTO track (title, album_id, duration) VALUES ('new_track', 1, '00:00:01');")
    };

    private static final Query[] delete = {
            new Query(QueryType.DELETE, "author", "DELETE FROM author WHERE author_id = (SELECT MAX(author_id) FROM author);"),
            new Query(QueryType.DELETE, "album", "DELETE FROM album WHERE album_id = (SELECT MAX(album_id) FROM album);"),
            new Query(QueryType.DELETE, "track", "DELETE FROM track WHERE track_id = (SELECT MAX(track_id) FROM track);")
    };

    private final Random random;

    public Queries() {
        random = new Random();
    }

    public Query getRandomQuery(QueryType type) {
        switch (type) {
            case CHANGE: return getRandomChangeQuery();
            case DELETE: return getRandomDeleteQuery();
            default: return getRandomSelectQuery();
        }
    }

    private Query getRandomSelectQuery() {
        int r = random.nextInt(select.length);
        return select[r];
    }

    private Query getRandomChangeQuery() {
        int r = random.nextInt(change.length);
        return change[r];
    }

    private Query getRandomDeleteQuery() {
        int r = random.nextInt(delete.length);
        return delete[r];
    }
}
