package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;

/**
 * A list of Steps in a Scenario.
 * Can be nested, i.e., other StepLists can constitute a StepList.
 * It is a composite of Components.
 *
 * @see Component
 * @see Step
 * @see Scenario
 */
public class StepList implements Component {

    private final ArrayList<Component> children;

    /**
     * A list of Steps in a Scenario.
     * Can be nested, i.e., other StepLists can constitute a StepList.
     * It is a composite of Components.
     *
     * @see Component
     * @see Step
     * @see Scenario
     */
    public StepList() {
        this.children = new ArrayList<>();
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
        for (Component child : children) {
            child.accept(visitor);
        }
    }
}
