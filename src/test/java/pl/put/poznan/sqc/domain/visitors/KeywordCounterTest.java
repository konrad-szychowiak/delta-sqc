package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordCounterTest {

    private KeywordCounter counter;

    @BeforeEach
    void
    setUp() {
        counter = new KeywordCounter();
    }

    @Test
    void
    emptyStep() {
        // arrange
        Step step = new Step("");

        // act
        step.accept(counter);

        // assert
        assertThat(counter.getCount())
            .isEqualTo(0);
    }

    @Test
    void
    noKeywordInStep() {
        Step step = new Step("Hello, world!");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(0);
    }

    @Test
    void
    beginsWithIF() {
        Step step = new Step("IF world then hello!");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    beginsWithELSE()
    {
        var step = new Step("ELSE be very sad :c");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    beginsWithFOREACH()
    {
        var step = new Step("FOR EACH Hello be happy ^^");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(1);
    }

    @Test
    void
    notSeparatedKeyword() {
        Step step = new Step("IFollow is an interface for follow.");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(0);
    }

    @Test
    void
    wrongCaseInKeyword()
    {
        Step step = new Step("if it were in UPPER case it would have been counted");

        step.accept(counter);

        assertThat(counter.getCount())
            .isEqualTo(0);
    }

    @Test
    void
    listOfCorrectKeywords()
    {
        // arrange - list with IF, ELSE, FOR EACH
        StepList list = new StepList();
        list.add(new Step(""));

        // act - visit the whole list

        // assert counted correctly
    }
}
