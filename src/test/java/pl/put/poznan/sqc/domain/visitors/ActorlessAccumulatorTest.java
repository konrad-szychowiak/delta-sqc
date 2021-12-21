package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActorlessAccumulatorTest {
    private Scenario scenario;
    private ActorlessAccumulator visitor;

    @BeforeEach
    void
    setUp()
    {
        ArrayList<String> batman = new ArrayList<>();
        ArrayList<String> alfred = new ArrayList<>();
        batman.add("Batman");
        alfred.add("Alfred");
        scenario = new Scenario("", batman, alfred);
        visitor = new ActorlessAccumulator();
    }

    @Test
    void stepWithActor() {
        // arrange
        scenario.add(new Step("Batman calls Alfred."));

        // act
        scenario.accept(visitor);

        // assert
        assertThat(visitor.getAccumulator().size())
            .isZero();
    }

    @Test
    void stepWithSystemActor() {
        // arrange
        scenario.add(new Step("Alfred eagerly responds."));

        // act
        scenario.accept(visitor);

        // assert
        assertThat(visitor.getAccumulator().size())
            .isZero();
    }

    @Test
    void withoutActor() {
        // arrange
        scenario.add(new Step("Superman is not an actor!"));

        // act
        scenario.accept(visitor);

        // assert
        assertThat(visitor.getAccumulator().size())
            .isEqualTo(1);
        assertThat(visitor.getAccumulator().get(0))
            .isEqualTo("Superman is not an actor!");
    }

    @Test
    void withAKeyword() {
        // arrange
        scenario.add(new Step("IF Alfred were younger, he'd be a superhero too."));

        // act
        scenario.accept(visitor);

        // assert
        assertThat(visitor.getAccumulator().size())
            .isZero();
    }

    @Test
    void withMessyCaseActor() {
        // arrange
        scenario.add(new Step("BaTmAn is rich!"));

        // act
        scenario.accept(visitor);

        // assert
        assertThat(visitor.getAccumulator().size())
            .isZero();
    }
}