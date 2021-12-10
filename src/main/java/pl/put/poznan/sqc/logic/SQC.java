package pl.put.poznan.sqc.logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.logic.visitors.Visitor;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SQC {
    // TODO: 2021-12-10 Scenario
    private final Scenario scenario;

    private SQC(Scenario scenario) {
        this.scenario = scenario;
    }

    private static void validateJSON(JSONObject json) throws InvalidScenarioJSONException {
        if (json.containsKey("title")
            && json.containsKey("actors")
            && json.containsKey("systemActors")
            && json.containsKey("steps")
        ) return;
        throw new InvalidScenarioJSONException();
    }

    public static SQC fromJSON(String json) throws ParseException, InvalidScenarioJSONException {
        Object obj = new JSONParser().parse(json);
        JSONObject jo = (JSONObject) obj;

        SQC.validateJSON(jo);

        String title = jo.get("title").toString();
        JSONArray actorsJSON = (JSONArray) jo.get("actors");
        JSONArray systemActorsJSON = (JSONArray) jo.get("systemActors");
        JSONArray steps = (JSONArray) jo.get("steps");

        Scenario scenario = new Scenario();
        // TODO: 2021-12-10 assign title 
        // TODO: 2021-12-10 assign actors 
        // TODO: 2021-12-10 assign system actors 
        // TODO: 2021-12-10 assign steps
        
        return new SQC(scenario);
    }

    public int runStepCounter() {
        Visitor counter = null;
        // TODO: 2021-12-10 StepCounter
        return -1;
    }

    public int runKeywordCounter() {
        Visitor counter = null;
        // TODO: 2021-12-10 KeywordCounter
        return -1;
    }
}
