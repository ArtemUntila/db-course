import util.Params;

public class Main {

    private static final String formatExceptionMessage =
            "Format: " +
            "<authorNum> <maxAlbumsPerAuthor> <maxTracksPerAlbum> " +
            "<userNum> <maxPlaylistsPerUser> <maxTracksPerPlaylist>" +
            "[debug]"
    ;

    public static void main(String[] args) {
        if (args.length < 6 || args.length > 7) {
            throw new IllegalArgumentException(formatExceptionMessage);
        }

        int authorNum;
        int maxAlbumsPerAuthor;
        int maxTracksPerAlbum;

        int userNum;
        int maxPlaylistsPerUser;
        int maxTracksPerPlaylist;

        boolean debug = false;

        try {
            authorNum = Integer.parseInt(args[0]);
            maxAlbumsPerAuthor = Integer.parseInt(args[1]);
            maxTracksPerAlbum = Integer.parseInt(args[2]);

            userNum = Integer.parseInt(args[3]);
            maxPlaylistsPerUser = Integer.parseInt(args[4]);
            maxTracksPerPlaylist = Integer.parseInt(args[5]);

            if (args.length == 7) debug = Integer.parseInt(args[6]) != 0;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(formatExceptionMessage);
        }

        Params params = new Params(
                authorNum, maxAlbumsPerAuthor, maxTracksPerAlbum,
                userNum, maxPlaylistsPerUser, maxTracksPerPlaylist
        );

        Inserter inserter = new Inserter(params, debug);
        inserter.execute();
    }
}
