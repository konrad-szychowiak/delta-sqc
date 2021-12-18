package pl.put.poznan.sqc.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.StepList;

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

        StepList stepList = new StepList();
        return new Scenario(
            // TODO: 2021-12-10 assign title
            title,
            // TODO: 2021-12-10 assign actors
            Collections.emptyList(),
            // TODO: 2021-12-10 assign system actors
            Collections.emptyList(),
            // TODO: 2021-12-10 assign steps
            stepList
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
