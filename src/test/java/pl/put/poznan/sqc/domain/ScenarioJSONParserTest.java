package pl.put.poznan.sqc.domain;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScenarioJSONParserTest {
    @Test
    public void
    invalidJSON() {
        String foo = "{\"title}";
        assertThrows(ParseException.class, () -> ScenarioJSONParser.parse(foo));
    }

    @Test
    public void
    invalidScenario() {
        String foo = "{\"name\": \"hello\", \"actors\": [], \"systemActors\": [], \"steps\": []}";

        assertThrows(InvalidScenarioException.class, () -> ScenarioJSONParser.parse(foo));
    }

    @Test
    public void
    validScenario() {
        String foo = "{\"title\": \"hello\", \"actors\": [], \"systemActors\": [], \"steps\": []}";

        assertDoesNotThrow(() -> ScenarioJSONParser.parse(foo));
    }

    @Test
    public void
    emptyScenario() throws InvalidScenarioException, ParseException {
        String foo = "{\"title\": \"empty\", \"actors\": [], \"systemActors\": [], \"steps\": []}";

        Scenario scenario = ScenarioJSONParser.parse(foo);

        assertThat(scenario.getTitle())
            .isEqualTo("empty");
        assertThat(scenario.getActors())
            .isEmpty();
        assertThat(scenario.getSystemActors())
            .isEmpty();
        assertThat(scenario.getSteps())
            .isEmpty();
    }

    @Test
    public void
    oneElementEachScenario() throws InvalidScenarioException, ParseException {
        String foo = "{\"title\": \"one element\", \"actors\": [\"one\"], \"systemActors\": [\"element\"], \"steps\": [\"step\"]}";

        Scenario scenario = ScenarioJSONParser.parse(foo);
        String title = scenario.getTitle();
        ArrayList<String> actors = scenario.getActors();
        ArrayList<String> systemActors = scenario.getSystemActors();
        var steps = scenario.getSteps();

        assertThat(title)
            .isEqualTo("one element");

        assertThat(actors)
            .isNotEmpty();
        assertThat(actors.size())
            .isEqualTo(1);
        assertThat(actors.get(0))
            .isEqualTo("one");

        assertThat(systemActors)
            .isNotEmpty();
        assertThat(systemActors.size())
            .isEqualTo(1);
        assertThat(systemActors.get(0))
            .isEqualTo("element");

        assertThat(steps.size())
            .isEqualTo(1);
        assertThat(steps.get(0))
            .isInstanceOf(Step.class);
        assertThat(((Step) steps.get(0)).getText())
            .isEqualTo("step");
    }

    @Test
    public void
    nestedStepList() throws InvalidScenarioException, ParseException {
        // TODO: 2021-12-20  
        String foo = "{\"title\":\"Dodanie książki\",\"actors\":[\"Bibliotekarz\"],\"systemActors\":[\"System\"],\"steps\":[{\"text\":\"Bibliotekarz pragnie dodać egzemplarze książki\",\"children\":[\"Bibliotekarz wybiera opcję definiowania egzemplarzy\",\"System prezentuje zdefiniowane egzemplarze\"]}]}";

        Scenario scenario = ScenarioJSONParser.parse(foo);
        var steps = scenario.getSteps();

        assertThat(steps.size())
            .isEqualTo(1);

        assertThat(steps.get(0))
            .isInstanceOf(StepList.class);

        StepList inner = (StepList) steps.get(0);

        assertThat(inner.getMainStep())
            .isExactlyInstanceOf(Step.class);

        assertThat(inner.getChildren().size())
            .isEqualTo(2);
    }
}