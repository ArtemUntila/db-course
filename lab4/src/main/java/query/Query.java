package query;

public class Query {
    public final QueryType type;
    public final String table;
    public final String text;

    public Query(QueryType type, String table, String text) {
        this.type = type;
        this.table = table;
        this.text = text;
    }
}
