package util;

public class Params {

    public final int authorNum;
    public final int maxAlbumsPerAuthor;
    public final int maxTracksPerAlbum;

    public final int userNum;
    public final int maxPlaylistsPerUser;
    public final int maxTracksPerPlaylist;

    public Params(
            int authorNum, int maxAlbumsPerAuthor, int maxTracksPerAlbum,
            int userNum, int maxPlaylistsPerUser, int maxTracksPerPlaylist
    ) {
        this.authorNum = authorNum;
        this.maxAlbumsPerAuthor = maxAlbumsPerAuthor;
        this.maxTracksPerAlbum = maxTracksPerAlbum;

        this.userNum = userNum;
        this.maxPlaylistsPerUser = maxPlaylistsPerUser;
        this.maxTracksPerPlaylist = maxTracksPerPlaylist;
    }
}
