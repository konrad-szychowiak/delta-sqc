package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

public interface Visitor {
    void visit(Scenario scenario);

    void visit(Step step);

    void visit(Steplist list);

}