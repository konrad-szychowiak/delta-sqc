package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

/**
 * Shared interface for visitors that will run on the Scenarios.
 * @see Scenario
 */
public interface Visitor {
    /**
     * Visit a scenario.
     * @param scenario Scenario which will be analysed
     * @see Scenario#accept(Visitor) 
     */
    void visit(Scenario scenario);

    /**
     * Visit a step.
     * @param step Step which will be analysed
     * @see Step#accept(Visitor) 
     */
    void visit(Step step);

    /**
     * Visit a list of steps.
     * @param list StepList which will be analysed.
     * @see StepList#accept(Visitor) 
     */
    void visit(StepList list);
}