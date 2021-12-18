package pl.put.poznan.sqc.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Scenario;

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

        Scenario scenario = new Scenario();
        // TODO: 2021-12-10 assign title
        // TODO: 2021-12-10 assign actors
        // TODO: 2021-12-10 assign system actors
        // TODO: 2021-12-10 assign steps


        return new Scenario();
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
