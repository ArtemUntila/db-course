package query.executor;

import query.Query;

import java.sql.ResultSet;

public class DefaultQueryExecutor extends QueryExecutor {

    @Override
    void executeSelect(Query query) {
        ResultSet rs = lc.executeResult(query.text);
    }

    @Override
    void executeChange(Query query) {
        lc.executeUpdate(query.text);
    }

}