package db;

import java.sql.*;

public abstract class Connector {

    private final Connection connection;
    private final Statement statement;

    public Connector(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't establish connection");
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't create SQL statement");
        }

    }

    public final Connection getConnection() {
        return connection;
    }

    public final void execute(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            close();
            throw new RuntimeException("Couldn't execute query: " + query);
        }
    }

    public final int executeInt(String query) {
        ResultSet rs;
        int result = 0;

        try {
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            close();
            throw new RuntimeException("Couldn't execute query: " + query);
        }

        try {
            if (rs.next()) result = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get int from ResultSet");
        }

        return result;
    }

    public final void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't close Connector");
        }
    }
}
