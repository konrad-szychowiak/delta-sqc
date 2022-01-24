package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepCollection;
import pl.put.poznan.sqc.domain.scenario.StepList;

public abstract class LevelCounterVisitor implements Visitor{



    @Override
    public void visit(Step step) {

    }

//    public void visit(Scenario scenario){};


    public void visit(StepList list){};

    public void visit(Scenario scenario){};

    public abstract StepCollection visit(StepCollection scenario, int level);

}
