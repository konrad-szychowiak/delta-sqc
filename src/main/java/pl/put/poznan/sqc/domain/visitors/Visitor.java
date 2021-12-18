package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;

public interface Visitor {
    void visit(Scenario scenario);

    // TODO: 2021-12-12
    void visit(Step step);

    // TODO: 2021-12-12
    void visit(StepList list);
}
