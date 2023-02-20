package util.table;

public enum Genre {
    Pop, Disco, Synth_Pop, Rap, Hip_Hop, Dance, Electronic, Techno, House, Trance, Drum_and_Bass, Dubstep, Ambient,
    New_Age, Alternative, Hardcore, Post_Hardcore, Rock, New_Wave, Post_Rock, Progressive_Rock, Stoner_Rock, Folk_Rock,
    Hard_Rock, Rock_and_Roll, Classical, Metal, Heavy_Metal, Progressive_Metal, Epic_Metal, Folk_Metal, Gothic_Metal,
    Industrial_Metal, Post_Metal, Thrash_Metal, Death_Metal, Black_Metal, Doom_Metal, Alternative_Metal, Nu_Metal,
    Metalcore, Soft, Lounge, Jazz, Blues, R_and_B, Country, Punk, Punk_Rock, Reggae;

    public static final int length = values().length;

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }
}
