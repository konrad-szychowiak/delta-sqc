package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.StepList;

/**
 * A shared class for concrete visitors that <b>count</b> something.
 *
 * <p>This class contains an encapsulated counter and the methods to increment and access it.
 * It is a piece of logic shared by concrete counters (based on the Visitor interface)
 * that are meant to count something in a Scenario.</p>
 *
 * @see Visitor
 * @see Scenario
 * @see StepCounter
 * @see KeywordCounter
 */
public abstract class CounterVisitor implements Visitor {
    /**
     * The amount of Scenario's elements that met the condition
     * specific to a concrete counter that extends CounterVisitor.
     */
    private int count = 0;

    /**
     * Increment the counter.
     *
     * <p>The value of the counter is incremented when some conditions in concrete visitors have been met.</p>
     *
     * <p>It is <code>protected</code> because it should only be used
     * by the concrete visitor based on this abstract class.</p>
     */
    protected void
    incrementCount() {this.count++;}

    /**
     * Get the value of the counter.
     *
     * @return the number of Scenario's elements that fulfill the conditions in concrete visitors
     * @see StepCounter
     * @see KeywordCounter
     */
    public int
    getCount() {return count;}

    /**
     * Visit a scenario.
     *
     * @param scenario Scenario which will be analysed
     * @see Scenario#accept(Visitor)
     */
    @Override
    public void
    visit(Scenario scenario) {}

    /**
     * Visit a list of steps.
     *
     * @param list StepList which will be analysed.
     * @see StepList#accept(Visitor)
     */
    @Override
    public void
    visit(StepList list) {}
}
