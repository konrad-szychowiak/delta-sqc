package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Scenario;

import static org.assertj.core.api.Assertions.assertThat;

class StepCounterTest {

    StepCounter counter;

    @BeforeEach
    void
    setUp() {
        counter = new StepCounter();
    }

    // *** //
    // Testing Empty
    // *** //

    @Test
    void
    givenNoSteps() {
        // GIVEN no steps

        // WHEN asked for the count

        // THEN the StepCounter returns 0
        assertThat(counter.getCount())
            .isZero();
    }

    @Test
    void
    givenEmptyStepList() {
        // GIVEN a list with no steps
        StepList list = new StepList();

        // WHEN asked to count the steps in it
        list.accept(counter);

        // THEN the StepCounter returns 0
        assertThat(counter.getCount())
            .isZero();
    }

    @Test
    void
    givenEmptyScenario() {
        // GIVEN a scenario with an empty list of steps
        Scenario scenario = new Scenario();

        // WHEN asked to count the steps in it
        scenario.accept(counter);

        // THEN the StepCounter returns 0
        assertThat(counter.getCount())
            .isZero();
    }

    // *** //
    // Testing One
    // *** //

    @Test
    void
    givenOneStep() {
        // GIVEN a single step
        Step step = new Step("");

        // WHEN asked to count the steps in it
        step.accept(counter);

        // THEN the StepCounter returns 1
        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    givenOneElementStepList() {
        // GIVEN a list with only one step
        Step step = new Step("");
        StepList list = new StepList();
        list.add(step);

        // WHEN asked to count the steps in it
        list.accept(counter);

        // THEN the StepCounter returns 1
        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    givenNestedOneElementStepList() {
        // GIVEN a list with a list with one step inside: [ [ Step ] ]
        Step step = new Step("");
        StepList innerList = new StepList();
        StepList outerList = new StepList();
        innerList.add(step);
        outerList.add(innerList);

        // WHEN asked to count the steps in it
        list.accept(counter);

        // THEN the StepCounter returns 1
        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    givenScenarioWithOneStep() {
        // GIVEN a scenario with a list of one step
        Step step = new Step("");
        StepList list = new StepList();
        list.add(step);
        Scenario scenario = new Scenario("", list /*, actors[0], systemActors[0] */);

        // WHEN asked to count the steps in it
        scenario.accept(counter);

        // THEN the StepCounter returns 1
        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    // *** //
    // Testing many
    // *** //

    // TODO: 2021-12-13  
    void
    givenStepListWithNSteps() {
        // GIVEN a list with N steps
        // WHEN asked to count the steps in it
        // THEN the StepCounter returns N
    }

    // TODO: 2021-12-13
    void
    givenScenarioWithNSteps() {
        // GIVEN a scenario with a list of N steps
        // WHEN asked to count the steps in it
        // THEN the StepCounter returns N
    }
}