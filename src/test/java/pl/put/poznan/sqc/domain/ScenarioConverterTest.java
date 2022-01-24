package pl.put.poznan.sqc.domain;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ScenarioConverterTest {


    @Test
    public void
    emptyScenario() throws InvalidScenarioException, ParseException {
        String foo = "{\"title\": \"empty\", \"actors\": [], \"systemActors\": [], \"steps\": []}";

        ArrayList<String> actor = new ArrayList<>();
        ArrayList<String> system = new ArrayList<>();
        Scenario scenario = new Scenario("empty", actor, system);

        ScenarioConverter converter = new ScenarioConverter(scenario);
        String scenarioJSON = converter.getJsonScenario();

        Scenario scenario2 = JSONConverter.parse(foo);
        ScenarioConverter converter2 = new ScenarioConverter(scenario2);
        String scenario2JSON = converter2.getJsonScenario();
        assertThat(scenarioJSON).isEqualTo(scenario2JSON);;


    }

    @Test
    public void
    oneElementEachScenario() throws InvalidScenarioException, ParseException {
        String foo = "{\"title\": \"one element\", \"actors\": [\"one\"], \"systemActors\": [\"element\"], \"steps\": [\"step\"]}";


        ArrayList<String> actor = new ArrayList<>();
        ArrayList<String> system = new ArrayList<>();
        actor.add("one");
        system.add("element");
        Scenario scenario = new Scenario("one element", actor, system);
        Step step = new Step("step");
        scenario.add(step);

        ScenarioConverter converter = new ScenarioConverter(scenario);
        String scenarioJSON = converter.getJsonScenario();

        Scenario scenario2 = JSONConverter.parse(foo);
        ScenarioConverter converter2 = new ScenarioConverter(scenario2);
        String scenario2JSON = converter2.getJsonScenario();
        assertThat(scenarioJSON).isEqualTo(scenario2JSON);

    }


    @Test
    public void nestedScenario() throws InvalidScenarioException, ParseException
    {
        ArrayList<String> actor = new ArrayList<>();
        ArrayList<String> system = new ArrayList<>();
        actor.add("Bibliotekarz");
        system.add("System");
        Scenario scenario = new Scenario("Dodanie książki", actor, system);


        StepList list = new StepList(new Step("Bibliotekarz pragnie dodać egzemplarze książki"));
        list.add(new Step("Bibliotekarz wybiera opcję definiowania egzemplarzy"));
        list.add(new Step("System prezentuje zdefiniowane egzemplarze"));
        scenario.add(list);

        ScenarioConverter converter = new ScenarioConverter(scenario);
        String scenarioJSON = converter.getJsonScenario();
        String foo = "{\"title\":\"Dodanie książki\",\"actors\":[\"Bibliotekarz\"],\"systemActors\":[\"System\"],\"steps\":[{\"text\":\"Bibliotekarz pragnie dodać egzemplarze książki\",\"steps\":[\"Bibliotekarz wybiera opcję definiowania egzemplarzy\",\"System prezentuje zdefiniowane egzemplarze\"]}]}";
        Scenario scenario2 = JSONConverter.parse(foo);
        ScenarioConverter converter2 = new ScenarioConverter(scenario2);
        String scenario2JSON = converter2.getJsonScenario();
        assertThat(scenarioJSON).isEqualTo(scenario2JSON);

    }
}
