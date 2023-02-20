import db.LocalConnector;
import generation.QueryGenerator;
import generation.RandomGenerator;
import util.Params;
import util.Tables;
import util.table.*;
import util.type.AlbumType;
import util.type.AuthorType;

public class Inserter {

    private final Params params;

    private final LocalConnector lc;
    private final RandomGenerator rg;
    private final QueryGenerator qg;

    public Inserter(Params params, boolean debug) {
        this.params = params;

        lc = new LocalConnector();
        rg = new RandomGenerator();
        qg = new QueryGenerator(debug);
    }

    public void execute() {
        initTableIDs();
        insertGenres();

        for (int i = 0; i < params.authorNum; i++) {
            insertAuthor();
        }

        for (int i = 0; i < params.userNum; i++) {
            insertUser();
        }

        lc.close();
    }

    // Avoid replacing existing data
    private void initTableIDs() {
        int author_id = lc.executeInt(qg.selectMaxIDQuery("author"));
        int album_id = lc.executeInt(qg.selectMaxIDQuery("album"));
        int track_id = lc.executeInt(qg.selectMaxIDQuery("track"));

        int user_id = lc.executeInt(qg.selectMaxIDQuery("user"));
        int playlist_id = lc.executeInt(qg.selectMaxIDQuery("playlist"));

        Author.setID(author_id);
        Album.setID(album_id);
        Track.setID(track_id);

        User.setID(user_id);
        Playlist.setID(playlist_id);
    }

    private void insertGenres()  {
        for (Genre genre : Genre.values()) {
            String insertGenre = qg.insertGenre(genre);
            lc.execute(insertGenre);
        }
    }

    private void insertAuthor() {
        Author author = Tables.newAuthor();

        String insertAuthor = qg.insertAuthor(author);
        String insertAuthorGenre = qg.insertAuthorGenre(Author.getID(), rg.randomGenreID());
        lc.execute(insertAuthor + insertAuthorGenre);

        int albumsNum = rg.randInt(params.maxAlbumsPerAuthor) + 1;
        for (int i = 0; i < albumsNum; i++) {
            insertAlbum();
        }
    }

    private void insertAlbum() {
        int tracksNum = rg.randInt(params.maxTracksPerAlbum) + 1;
        AlbumType type = AlbumType.defineType(tracksNum);
        Album album = Tables.newAlbum(Author.getID(), type);

        String insertAlbum = qg.insertAlbum(album);
        String insertAlbumGenre = qg.insertAlbumGenre(Album.getID(), rg.randomGenreID());
        lc.execute(insertAlbum + insertAlbumGenre);

        for (int i = 0; i < tracksNum; i++) {
            insertTrack();
        }
    }

    private void insertTrack() {
        Track track = Tables.newTrack(Album.getID());

        String insertTrack = qg.insertTrack(track);
        String insertAuthorTrack = qg.insertAuthorTrack(Author.getID(), Track.getID(), AuthorType.main);
        lc.execute(insertTrack + insertAuthorTrack);
    }

    private void insertUser() {
        User user = Tables.newUser();

        String insertUser = qg.insertUser(user);
        lc.execute(insertUser);

        int playlistsNum = rg.randInt(params.maxPlaylistsPerUser) + 1;
        for (int i  = 0; i < playlistsNum; i++) {
            insertPlaylist();
        }
    }

    private void insertPlaylist() {
        Playlist playlist = Tables.newPlaylist(User.getID());

        String insertPlaylist = qg.insertPlaylist(playlist);
        lc.execute(insertPlaylist);

        int tracksNum = rg.randInt(params.maxTracksPerPlaylist) + 1;
        int playlist_id = Playlist.getID();
        for (int i = 0; i < tracksNum; i++) {
            int track_id = rg.randomTrackID();

            String insertPlaylistTrack = qg.insertPlaylistTrack(playlist_id, track_id);
            lc.execute(insertPlaylistTrack);
        }
    }
}
