import query.Queries;
import query.Query;
import query.QueryType;
import query.executor.QueryExecutor;
import util.Counter;
import util.Measurer;
import util.Params;

import java.util.Random;

public class Executor {

    private final Params params;
    private final QueryExecutor qe;

    private final Random random;
    private final Queries queries;
    private final Counter counter;

    public Executor(Params params, QueryExecutor qe) {
        this.params = params;
        this.qe = qe;

        random = new Random();
        queries = new Queries();
        counter = new Counter();
    }

    public void execute() {
        for (int i = 0; i < params.num; i++) {
            QueryType type = getQueryType();
            Query query = queries.getRandomQuery(type);
            long l = Measurer.measure(() -> qe.execute(query));
            counter.append(type, l);
        }

        System.out.println(qe.getClass().getName());
        counter.printStatistics();
    }

    private QueryType getQueryType() {
        QueryType type;
        int r = random.nextInt(params.sum);
        if (r < params.select) type = QueryType.SELECT;
        else if (r < params.select + params.change) type = QueryType.CHANGE;
        else type = QueryType.DELETE;

        return type;
    }
}
