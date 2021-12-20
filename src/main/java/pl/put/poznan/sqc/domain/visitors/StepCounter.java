package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.SQCService;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;

/**
 * Counter of steps in a scenario.
 *
 * <p>Increment the counter for each Step encountered in a Scenario,
 * no matter the step's content.</p>
 *
 * @see Step
 * @see Scenario
 * @see SQCService#getStepCount()
 */
public class StepCounter extends CounterVisitor {
    
    /**
     * Visit a step in a scenario, incrementing the counter every time.
     *
     * @param step Step which will be analysed
     * @see Step
     * @see Scenario
     */
    @Override
    public void
    visit(Step step) {
        this.incrementCount();
    }
}
