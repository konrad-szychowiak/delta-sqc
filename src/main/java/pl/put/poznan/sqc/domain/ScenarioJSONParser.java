package pl.put.poznan.sqc.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;
import java.util.Collections;


public class ScenarioJSONParser {

    private static ArrayList jsonArrayToArrayList(JSONArray jsonArray)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            int len = jsonArray.size();
            for (int i=0;i<len;i++){
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }

    static Scenario parse(String jsonString) throws ParseException, InvalidScenarioException {
        // TODO: 2021-12-12 entire class
        Object obj = new JSONParser().parse(jsonString);
        JSONObject jo = (JSONObject) obj;

        ScenarioJSONParser.validate(jo);

        String title = jo.get("title").toString();
        JSONArray actorsJSON = (JSONArray) jo.get("actors");
        JSONArray systemActorsJSON = (JSONArray) jo.get("systemActors");
        JSONArray steps = (JSONArray) jo.get("steps");
        ArrayList<String> actorsList = jsonArrayToArrayList(actorsJSON);
        ArrayList<String> systemActorsList = jsonArrayToArrayList(systemActorsJSON);
        ArrayList<String> stepsArrayList = jsonArrayToArrayList(steps);
        StepList stepList = StepList.withNoMain();
        for (String step : stepsArrayList)
        {
            Step stepObject = new Step(step);
            stepList.add(stepObject);
        }
        return new Scenario(
            title,
            actorsList,
            systemActorsList
        );
    }

    private static JSONObject serialize(Scenario scenario)  {

        var jsonObject = new JSONObject();

        jsonObject.put("title", scenario.getTitle());
        jsonObject.put("actors", scenario.getActors());
        jsonObject.put("systemActors", scenario.getSystemActors());
        jsonObject.put("steps", scenario.getSteps());
        return jsonObject;
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