package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

public class KeywordCounter extends CounterVisitor {
    @Override
    public void visit(Scenario scenario) {

    }

    @Override
    public void
    visit(Step step) {

        String temp_text = step.getText();

        try{
            if(temp_text.substring(0,3) == "IF "){
                this.incrementCount();

            }else if(temp_text.substring(0,5) == "ELSE "){
                this.incrementCount();

            }else if(temp_text.substring(0,9) == "FOR EACH "){
                this.incrementCount();
            }
        } catch (StringIndexOutOfBoundsException e){
            return;
        }

    }

    @Override
    public void
    visit(StepList list) {

    }
}
