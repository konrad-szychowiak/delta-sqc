package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

/**
 * A step in a scenario.
 *
 * <p>Represents an action to be done when performing the scenario.
 * As a Component, it is a constituent od a StepList (which itself can constitute another StepList)</p>
 *
 * @see Scenario
 * @see StepList
 */
public class Step implements Component {

    private final String text;

    /**
     * Create a step with a given text describing the action to be taken.
     * @param text string representing the step.
     */
    public Step(String text) {
        this.text = text;
    }

    /**
     * Get a string representing the step in a scenario.
     * @return text of the step
     */
    public String
    getText() {
        return text;
    }

    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
    }
}
