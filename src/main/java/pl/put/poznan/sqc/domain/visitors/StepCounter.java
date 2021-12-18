package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;

public class StepCounter extends CounterVisitor {
    @Override
    public void visit(Scenario scenario) {
        // TODO: 2021-12-12
    }


    @Override
    public void
    visit(StepList list) {}


    @Override
    public void
    visit(Step step) {
        this.incrementCount();
    }
}
