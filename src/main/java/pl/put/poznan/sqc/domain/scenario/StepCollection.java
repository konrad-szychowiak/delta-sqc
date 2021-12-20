package pl.put.poznan.sqc.domain.scenario;

/**
 * An interface for collections of Steps.
 * @see Step
 * @see StepList
 * @see Scenario
 */
public interface StepCollection {

    /**
     * Add a Component to the collection.
     *
     * @param item Component to be added
     * @see Component
     */
    void
    add(Component item) ;

    /**
     * Add a Component to the collection.
     *
     * @param item Component to be removed.
     * @see Component
     */
    void
    remove(Component item) ;
}
