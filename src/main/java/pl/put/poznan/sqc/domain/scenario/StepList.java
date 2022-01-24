package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;

/**
 * A Sub-Scenario; a collection of Components.
 *
 * <p>It is made of one main step that is an element of the higher (Sub-)Scenario
 * and a list of Components that represent the sub-scenario's elements.</p>
 *
 * @see Step
 * @see Scenario
 * @see Component
 * @see StepCollection
 */
public class StepList implements Component, StepCollection {
    /**
     * Children Components of the StepList
     */
    private final ArrayList<Component> children;
    /**
     * Main (central) step of the list
     */
    private Step mainStep;


    /**
     * A Sub-Scenario—a list of Steps in a Scenario.
     * Can be nested, i.e., other StepLists can constitute a StepList.
     * It is a composite of Components.
     *
     * @param mainStep a main step representing this sub-scenario
     *
     * @see Component
     * @see Step
     * @see Scenario
     */
    public StepList(Step mainStep) {
        this.mainStep = mainStep;
        this.children = new ArrayList<>();
    }

    /**
     * A Sub-Scenario—a list of Steps in a Scenario.
     * Can be nested, i.e., other StepLists can constitute a StepList.
     * It is a composite of Components.
     *
     * @param mainStepText a text from which new main Step will be created
     *
     * @see StepList#mainStep
     * @see Component
     * @see Step
     * @see Scenario
     */
    public StepList(String mainStepText) {
        this.mainStep = new Step(mainStepText);
        this.children = new ArrayList<>();
    }

    /**
     * @deprecated
     */
    public static StepList
    withNoMain() {
        return new StepList(new Step(""));
    }

    /**
     * Get all the items in the StepList.
     *
     * @return ArrayList of Components: items in the list
     * @see Component
     */
    public ArrayList<Component>
    getChildren() {
        return children;
    }

    /**
     * Add an item to the StepList.
     *
     * @param item Component to be added
     * @see Component
     */
    public void
    add(Component item) {
        children.add(item);
    }

    /**
     * Remove an item from the StepList.
     *
     * @param item Component to be removed.
     * @see Component
     */
    public void
    remove(Component item) {
        children.remove(item);
    }

    /**
     * Access the main Step of the StepList.
     *
     * @return the main step
     * @see Step
     */
    public Step getMainStep() {
        return mainStep;
    }

    /**
     * @param step a new main step
     * @deprecated main step will be final and set by constructor
     */
    public void
    setMainStep(Step step) {
        this.mainStep = step;
    }

    /**
     * Give the visitor information about the current instance
     * and pass it to all the items in the StepList.
     *
     * @param visitor Visitor to give information to.
     * @see Visitor
     * @see Scenario#accept(Visitor)
     * @see Step#accept(Visitor)
     */
    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
        mainStep.accept(visitor);
        for (Component child : children) {
            child.accept(visitor);
        }
    }

}
