package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

public class LevelCounter extends LevelCounterVisitor{
    private int level;
    private ArrayList<String> stepsTexts;

    public LevelCounter(int level)
    {
        this.level  = level;
    }


    @Override
    public void visit(StepList stepList) {
        ArrayList<Component> steps = stepList.getChildren();
        this.level --;

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
                stepsTexts.add(((Step) step).getText());
            }
        }
    }

    public ArrayList<String> getStepsTexts() {
        return stepsTexts;
    }



}
