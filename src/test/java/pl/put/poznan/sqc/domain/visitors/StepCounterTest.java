package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        StepList list = StepList.withNoMain();

        // WHEN asked to count the steps in it
        list.accept(counter);

        // THEN the StepCounter returns 1 (step list contains a core step)
        assertThat(counter.getCount())
            .isNotZero()
            .isEqualTo(1);
    }

    @Test
    void
    givenEmptyScenario() {
        // GIVEN a scenario with an empty list of steps
        Scenario scenario = mock(Scenario.class);
        when(scenario.getSteps()).thenReturn(new ArrayList<>());

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
        StepList list = StepList.withNoMain();
        list.add(step);

        // WHEN asked to count the steps in it
        list.accept(counter);

        // THEN the StepCounter returns 2
        assertThat(counter.getCount())
            .isEqualTo(2);
    }

    @Test
    void
    givenNestedOneElementStepList() {
        // GIVEN a list with a list with one step inside: [ [ Step ] ]
        Step step = new Step("1. step");
        StepList innerList = new StepList("2. inner");
        StepList outerList = new StepList("3. outer");
        innerList.add(step);
        outerList.add(innerList);

        // WHEN asked to count the steps in it
        outerList.accept(counter);

        // THEN the StepCounter returns 3 (each step-list has a main step itself)
        assertThat(counter.getCount())
            .isEqualTo(3);
    }

    @Test
    void
    givenScenarioWithOneStep() {
        // GIVEN a scenario with a list of one step
        Step step = new Step("");
        Scenario scenario = new Scenario("", null, null);
        scenario.add(step);

        // WHEN asked to count the steps in it
        scenario.accept(counter);

        // THEN the StepCounter returns 1
        assertThat(counter.getCount())
            .isEqualTo(1);
    }
}