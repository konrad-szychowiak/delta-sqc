package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

public class LevelCounter extends LevelCounterVisitor{
    private int level;
    private Scenario cuttedScenario;

    public LevelCounter(int level)
    {
        this.level  = level;
    }

    @Override
    public void visit(Scenario scenario) {
        this.cuttedScenario = new Scenario(scenario.getTitle(), scenario.getActors(), scenario.getSystemActors());
        this.level --;
        ArrayList<Component> steps  = scenario.getSteps();
        for(Component step : steps)
        {
            if (step instanceof StepList) {
                if (level > 0)
                {
                    visit((StepList)step);
                }
            }
            else if (step instanceof Step)
            {
                this.cuttedScenario.add(((Step) step));
            }
        }
    }

    public Scenario getCuttedScenario() {
        return this.cuttedScenario;
    }

}
