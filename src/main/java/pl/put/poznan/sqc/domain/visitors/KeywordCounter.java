package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Step;
/**
 *Concrete class of CounterVisitor
 *<p>Increment the counter for each keyword encountered</p>
 *<p>The keywords are:
 *<b>IF</b>
 *<b>ELSE</b>
 *<b>FOR EACH</b></p>
 * @see CounterVisitor
 * @see Visitor
 */

public class KeywordCounter extends CounterVisitor {
    @Override
    public void
    /** Increment the counter if one of keywords is encountered
     * Checks whether the text of step starts with any of keywords and a space
     * If encounters exception due to too small text returns nothing: no keywords in text
     * @see CounterVisitor
     */
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
