package util.table;

public class User {
    private static int user_id;

    public static int getID() {
        return user_id;
    }

    public static void setID(int id) {
        user_id = id;
    }

    public String name;

    public User(String name) {
        user_id++;

        this.name = name;
    }
}
