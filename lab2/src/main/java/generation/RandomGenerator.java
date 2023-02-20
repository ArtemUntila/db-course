package generation;

import java.time.LocalDate;
import java.util.Random;

import util.Country;
import util.table.Genre;
import util.table.Track;

public class RandomGenerator {

    private final Random random;

    public RandomGenerator() {
        random = new Random();
    }

    public int randInt(int bound) {
        return random.nextInt(bound);
    }

    public int randomGenreID() {
        return randInt(Genre.length) + 1;
    }

    public int randomTrackID() {
        return randInt(Track.getID()) + 1;
    }

    public String randomCountry() {
        Country[] countries = Country.values();
        int r = randInt(countries.length);
        Country country = countries[r];
        return country.name();
    }

    private static final int dateMax = (int) LocalDate.now().toEpochDay();

    public String randomDate() {
        int r = randInt(dateMax);
        LocalDate ld = LocalDate.ofEpochDay(r);
        return ld.toString();
    }

    public String randomTime() {
        int r = randInt(600) + 1;
        int hh = 0;
        int mm = r / 60;
        int ss = r % 60;
        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }


    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
    private static final int alphabetLength = alphabet.length();

    public String randomString(int length) {
        if (length <= 0) return null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = randInt(alphabetLength);
            char c = alphabet.charAt(r);
            sb.append(c);
        }
        return sb.toString();
    }

    public String randomLyrics() {
        int length = randInt(91) + 10;
        return randomString(length);
    }
}
