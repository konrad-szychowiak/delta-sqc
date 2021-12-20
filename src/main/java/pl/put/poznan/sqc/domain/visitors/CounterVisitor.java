package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.StepList;
public abstract class CounterVisitor implements Visitor {
    private int count = 0;

    protected void
    incrementCount() {this.count++;}

    public int
    getCount() {return count;}
    @Override
    public void
    visit(Scenario scenario) {}
    @Override
    public void
    visit(StepList list) {}
}
