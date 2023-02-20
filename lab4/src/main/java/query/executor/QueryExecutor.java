package query.executor;

import db.LocalConnector;
import query.Query;
import query.QueryType;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class QueryExecutor {

    LocalConnector lc;

    public QueryExecutor() {
        lc = new LocalConnector();
    }

    public void execute(Query query) {
        if (query.type == QueryType.SELECT) {
            executeSelect(query);
        } else {
            executeChange(query);
        }
    }

    public void close() {
        lc.close();
    }

    String resultSetToString(ResultSet rs) {
        StringBuilder sb = new StringBuilder();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) sb.append("  ");
                    String columnValue = rs.getString(i);
                    sb.append(columnValue);
                }
                sb.append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't convert ResultSet to String");
        }
        return sb.toString();
    }

    abstract void executeSelect(Query query);

    abstract void executeChange(Query query);
}
