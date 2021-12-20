package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Step;

public class KeywordCounter extends CounterVisitor {
    @Override
    public void
    visit(Step step) {

        String temp_text = step.getText();

        try{
            if(temp_text.substring(0, 3).equals("IF ")){
                this.incrementCount();

            }else if(temp_text.substring(0, 5).equals("ELSE ")){
                this.incrementCount();

        }else if(temp_text.substring(0, 9).equals("FOR EACH ")){
                this.incrementCount();
            }
        } catch (StringIndexOutOfBoundsException e){
            return;
        }
    }
}
