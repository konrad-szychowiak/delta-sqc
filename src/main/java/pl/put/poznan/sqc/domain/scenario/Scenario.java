package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;

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
public class Scenario implements Visitable, StepCollection {
    /**
     * Title of the scenario
     */
    private final String title;
    /**
     * (Human) actors in the scenario
     */
    private final ArrayList<String> actors;
    /**
     * System actors in the scenario
     */
    private final ArrayList<String> systemActors;
    /**
     * Collection of steps in the scenario
     */
    private final ArrayList<Component> steps;

    /**
     * Creates an immutable scenario based on all it's required properties:
     * title, actors (and system actors) and a list of steps.
     *
     * @param title        title of the scenario
     * @param actors       a list of (user) actors in the scenario
     * @param systemActors a list of system actors in the scenario
     * @see StepList
     * @see Step
     */
    public Scenario(String title, ArrayList<String> actors, ArrayList<String> systemActors) {
        this.title = title;
        this.actors = actors;
        this.systemActors = systemActors;
        this.steps = new ArrayList<>();
    }

    /**
     * Get the scenario's title.
     *
     * @return title as a string
     */
    public String
    getTitle() {
        return title;
    }

    /**
     * Get the (user) actors in the scenario.
     *
     * @return actors as an array of strings
     */
    public ArrayList<String>
    getActors() {
        return actors;
    }

    /**
     * Get the <b>system</b> actors in the scenario.
     *
     * @return system actors as an array of strings
     */
    public ArrayList<String>
    getSystemActors() {
        return systemActors;
    }

    /**
     * Get the steps in the scenario.
     *
     * @return the steps (and lists of them) as (potentially nested) StepList
     * @see StepList
     * @see Step
     */
    public ArrayList<Component>
    getSteps() {
        return steps;
    }

    /**
     * Disclose the data about the scenario to a visitor and pass it to the list of steps.
     *
     * @param visitor Visitor to give information to.
     * @see StepList
     * @see Step
     */
    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
        for (Component child : this.getSteps()) {
            child.accept(visitor);
        }
    }

    @Override
    public void add(Component item) {
        steps.add(item);
    }

    @Override
    public void remove(Component item) {
        steps.remove(item);
    }
}
