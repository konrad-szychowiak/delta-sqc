package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Step;

public class StepCounter extends CounterVisitor {
    @Override
    public void
    visit(Step step) {
        this.incrementCount();
    }
}
