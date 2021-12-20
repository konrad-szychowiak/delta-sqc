package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;

public interface Visitor {
    void visit(Scenario scenario);

    void visit(Step step);

    void visit(Steplist list);

}