package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

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
