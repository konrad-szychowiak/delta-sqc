package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.SQCService;
import pl.put.poznan.sqc.domain.scenario.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Concrete class of CounterVisitor
 * <p>Increment the counter for each keyword encountered in Scenario's Steps</p>
 * <p>The keywords are:
 * <b>IF</b>,
 * <b>ELSE</b>,
 * <b>FOR EACH</b>.</p>
 *
 * @see CounterVisitor
 * @see Visitor
 * @see pl.put.poznan.sqc.domain.scenario.Scenario
 * @see Step
 * @see SQCService#getKeywordCount()
 */
public class KeywordCounter extends CounterVisitor {

    /**
     * Increment the counter if one of keywords is encountered.
     *
     * <p>Checks whether the text of step starts with any of keywords and a space.</p>
     *
     * @see CounterVisitor
     */
    @Override
    public void
    visit(Step step) {
        String temp_text = step.getText();
        Matcher matcher = Pattern.compile("^(IF|ELSE|FOR EACH)[\\s:]").matcher(temp_text);

        if (matcher.find())
            this.incrementCount();
    }
}
