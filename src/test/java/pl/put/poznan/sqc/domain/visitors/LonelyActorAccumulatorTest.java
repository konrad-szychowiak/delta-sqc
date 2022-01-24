package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LonelyActorAccumulatorTest {

    @Test
    void visit() {
        final String BATMAN = "batman";
        final String ALFRED = "alfred";

        ArrayList<String> batman = new ArrayList<>();
        batman.add(BATMAN);
        batman.add("Robin");

        ArrayList<String> alfred = new ArrayList<>();
        alfred.add(ALFRED);

        Scenario scenario = new Scenario("", batman, alfred);
        scenario.add(new Step("Batman calls Alfred."));


        var counter = new LonelyActorAccumulator();
        scenario.accept(counter);

        assertThat(counter.getLonely())
            .hasSize(1);
    }
}