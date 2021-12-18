package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * A scenario that will be analysed by the application.
 *
 * <p>It has a title, a list of actors (user- and system actors)
 * and a list of steps that needs to be taken to perform the whole scenario.</p>
 *
 *
 * <p>Scenarios are analysed by Visitors.
 * The way of accessing a scenario by a visitor is defined by the <code>Visitable</code> interface</p>
 *
 * @see StepList
 * @see Step
 * @see Visitable
 * @see Visitor
 */
public class Scenario implements Visitable {
    private final String title;
    private final ArrayList<String> actors;
    private final ArrayList<String> systemActors;
    private final StepList steps;

    /**
     * Creates an immutable scenario based on all it's required properties:
     * title, actors (and system actors) and a list of steps.
     *
     * @param title        title of the scenario
     * @param actors       a list of (user) actors in the scenario
     * @param systemActors a list of system actors in the scenario
     * @param steps        a list of steps in the scenario
     * @see StepList
     * @see Step
     */
    public Scenario(String title, ArrayList<String> actors, ArrayList<String> systemActors, StepList steps) {
        this.title = title;
        this.actors = actors;
        this.systemActors = systemActors;
        this.steps = steps;
    }

    /**
     * Get the scenario's title.
     * @return title as a string
     */
    public String
    getTitle() {
        return title;
    }

    /**
     * Get the (user) actors in the scenario.
     * @return actors as an array of strings
     */
    public List<String>
    getActors() {
        return actors;
    }

    /**
     * Get the <b>system</b> actors in the scenario.
     * @return system actors as an array of strings
     */
    public List<String>
    getSystemActors() {
        return systemActors;
    }

    /**
     * Get the steps in the scenario.
     * @return the steps (and lists of them) as (potentially nested) StepList
     * @see StepList
     * @see Step
     */
    public StepList
    getSteps() {
        return steps;
    }

    /**
     * Disclose the data about the scenario to a visitor and pass it to the list of steps.
     * @param visitor Visitor to give information to.
     * @see StepList
     * @see Step
     */
    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
        steps.accept(visitor);
    }
}
