import query.executor.CachedQueryExecutor;
import query.executor.DefaultQueryExecutor;
import query.executor.QueryExecutor;
import util.Params;

public class Main {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        int select = Integer.parseInt(args[1]);
        int change = Integer.parseInt(args[2]);
        int delete = Integer.parseInt(args[3]);

        Params params = new Params(num, select, change, delete);

        QueryExecutor dqe = new DefaultQueryExecutor();
        Executor defaultExecutor = new Executor(params, dqe);
        defaultExecutor.execute();
        dqe.close();

        QueryExecutor cqe = new CachedQueryExecutor(10);
        Executor cachedExecutor = new Executor(params, cqe);
        cachedExecutor.execute();
        cqe.close();
    }
}
