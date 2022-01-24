package pl.put.poznan.sqc.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;


public class JSONConverter {

    private static ArrayList<String>
    jsonArrayToArrayList(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }

    private static StepList
    parseStepList(String jsonString) throws ParseException {
        var jo = convertToJSONObject(jsonString);

        String mainStepText = jo.get("text").toString();
        ArrayList<String> stepsStringList = jsonArrayToArrayList((JSONArray) jo.get("steps"));

        StepList stepList = new StepList(mainStepText);

        for (String stepString : stepsStringList) {
            var component = parseComponent(stepString);
            stepList.add(component);
        }

        return stepList;
    }

    private static Component
    parseComponent(String text) throws ParseException {
        if (text.startsWith("{")) return parseStepList(text);
        return new Step(text);
    }

    private static JSONObject
    convertToJSONObject(String jsonString) throws ParseException {
        Object obj = new JSONParser().parse(jsonString);
        return (JSONObject) obj;
    }

    static Scenario parse(String jsonString) throws ParseException, InvalidScenarioException {
        var jo = convertToJSONObject(jsonString);
        JSONConverter.validate(jo);

        String title = jo.get("title").toString();
        JSONArray actorsJSON = (JSONArray) jo.get("actors");
        JSONArray systemActorsJSON = (JSONArray) jo.get("systemActors");
        JSONArray stepsJSON = (JSONArray) jo.get("steps");

        ArrayList<String> actorsList = jsonArrayToArrayList(actorsJSON);
        ArrayList<String> systemActorsList = jsonArrayToArrayList(systemActorsJSON);
        ArrayList<String> stepsAsStrings = jsonArrayToArrayList(stepsJSON);

        Scenario scenario = new Scenario(
                title,
                actorsList,
                systemActorsList
        );

        for (String stepString : stepsAsStrings) {
            var component = parseComponent(stepString);
            scenario.add(component);
        }

        return scenario;
    }

    private static JSONObject serialize(Scenario scenario) {

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
