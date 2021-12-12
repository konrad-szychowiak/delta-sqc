package pl.put.poznan.sqc.domain.visitors;

public abstract class CounterVisitor
    implements Visitor {
    private int count = 0;

    public int
    getCount() { return count; }
}
