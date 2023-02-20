package util.type;

public enum AlbumType {
    album, single, EP;

    public static AlbumType defineType(int tracksNum) {
        if (tracksNum <= 0)  return null;

        if (tracksNum == 1) return single;
        else if (tracksNum <= 4) return EP;
        else return album;
    }
}
