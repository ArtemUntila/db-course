package util;

public class Params {
    public final int num;
    public final int select;
    public final int change;
    public final int delete;
    public final int sum;

    public Params(int num, int select, int change, int delete) {
        this.num = num;
        this.select = select;
        this.change = change;
        this.delete = delete;

        sum = this.select + this.change + this.delete;
    }
}
