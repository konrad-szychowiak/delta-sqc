package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.*;

import java.util.ArrayList;

public class LevelCounter extends LevelCounterVisitor{
    private int level;
    private Scenario cuttedScenario;

    public LevelCounter(int level)
    {
        this.level  = level;
    }

    @Override
    public StepCollection  visit(StepCollection scenario, int level) {
        if (scenario instanceof Scenario) {
            this.cuttedScenario = new Scenario(((Scenario)scenario).getTitle(), ((Scenario)scenario).getActors(), ((Scenario)scenario).getSystemActors());
            ArrayList<Component> steps = ((Scenario)scenario).getSteps();
            for (Component step : steps) {
                if (step instanceof StepList) {
                    if (level -1>= 0 ) {
                        this.cuttedScenario.add((Component) visit((StepList) step, level - 1));
                    }
                } else if (step instanceof Step) {
                    if (level >= 0) {
                        System.out.println(level);
                        this.cuttedScenario.add(((Step) step));
                    }
                }
            }
        }
        else if (scenario instanceof StepList)
        {
            StepList tempStepList  = new StepList(((StepList) scenario).getMainStep());
            ArrayList<Component> steps = ((StepList) scenario).getChildren();
            for (Component step : steps) {
                if (step instanceof StepList) {
                    if (level >= 0) {
                        if (level -1>= 0 ) {
                            tempStepList.add((Component) visit((StepList) step, level - 1));
                        }
                    }
                } else if (step instanceof Step) {
                        tempStepList.add((Step) step);
                    }
            }
            return tempStepList;
        }

        return this.cuttedScenario;
    }

    public Scenario getCuttedScenario() {
        return this.cuttedScenario;
    }

}
