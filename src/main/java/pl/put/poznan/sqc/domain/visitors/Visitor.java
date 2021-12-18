package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

public interface Visitor {
    void visit(Scenario scenario);

    // TODO: 2021-12-12
    void visit(Step step);

    // TODO: 2021-12-12
    void visit(StepList list);
}
