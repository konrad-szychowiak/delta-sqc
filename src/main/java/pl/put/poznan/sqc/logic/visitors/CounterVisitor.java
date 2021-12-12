package pl.put.poznan.sqc.logic.visitors;

import pl.put.poznan.sqc.logic.Scenario;

public abstract class CounterVisitor
    implements Visitor {
    private int count = 0;

    public int
    getCount() { return count; }

    @Override
    public void
    visit(Scenario scenario) {}
}
