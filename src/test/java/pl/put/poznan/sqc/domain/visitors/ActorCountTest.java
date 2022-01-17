package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;

import java.util.ArrayList;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActorCountTest {

    @Test
    void visit() {
        final String BATMAN = "batman";
        final String ALFRED = "alfred";

        ArrayList<String> batman = new ArrayList<>();
        batman.add(BATMAN);

        ArrayList<String> alfred = new ArrayList<>();
        alfred.add(ALFRED);

        Scenario scenario = new Scenario("", batman, alfred);
        scenario.add(new Step("Batman calls Alfred."));


        ActorCount counter = new ActorCount();
        scenario.accept(counter);

        System.out.printf(counter.getActorCounter().toString());

        assertThat(counter.getActorCounter().get(BATMAN))
            .isEqualTo(1);

        assertThat(counter.getActorCounter().get(ALFRED))
            .isEqualTo(1);
    }
}