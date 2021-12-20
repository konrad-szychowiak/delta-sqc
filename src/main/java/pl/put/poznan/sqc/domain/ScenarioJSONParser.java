package pl.put.poznan.sqc.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;
import java.util.Collections;

public class ScenarioJSONParser {
    static Scenario
    parse(String jsonString) throws ParseException, InvalidScenarioException {
        // TODO: 2021-12-12 entire class
        Object obj = new JSONParser().parse(jsonString);
        JSONObject jo = (JSONObject) obj;

        ScenarioJSONParser.validate(jo);

        String title = jo.get("title").toString();
        JSONArray actorsJSON = (JSONArray) jo.get("actors");
        JSONArray systemActorsJSON = (JSONArray) jo.get("systemActors");
        JSONArray steps = (JSONArray) jo.get("steps");

        StepList list = new StepList();
        list.add(new Step("IF world say hello!"));
        list.add(new Step("ELSE don't say anything"));
        list.add(new Step("FOR EACH hello say greetings"));
        list.add(new Step("foo bar baz"));

        return new Scenario(
            title,
            new ArrayList<>(),
            new ArrayList<>(),
            list
        );
    }


    private static void
    validate(JSONObject json) throws InvalidScenarioException {
        if (json.containsKey("title")
            && json.containsKey("actors")
            && json.containsKey("systemActors")
            && json.containsKey("steps")
        ) return;
        throw new InvalidScenarioException();
    }
}
