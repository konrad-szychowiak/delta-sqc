package pl.put.poznan.sqc.domain.visitors;

public abstract class CounterVisitor
    implements Visitor {
    private int count = 0;

    protected void
    incrementCount() { this.count++; }

    public int
    getCount() { return count; }
}
