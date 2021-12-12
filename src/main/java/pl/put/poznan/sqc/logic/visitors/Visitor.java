package pl.put.poznan.sqc.logic.visitors;

import pl.put.poznan.sqc.logic.Scenario;

public interface Visitor {
    void visit(Scenario scenario);

    // TODO: 2021-12-12
    //  public void visit(Step step);

    // TODO: 2021-12-12
    //  public void visit(StepList list);
}
