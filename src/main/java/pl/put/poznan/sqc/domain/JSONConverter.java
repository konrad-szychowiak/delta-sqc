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


public class JSONConverter {

    private static ArrayList jsonArrayToArrayList(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }

    static StepList parseSteps(JSONObject steps) throws ParseException {
        String mainStepText = (String) steps.get("text");
        Step mainStep = new Step(mainStepText);
        StepList stepList = new StepList(mainStep);
        ArrayList<String> childrenList = (ArrayList<String>) steps.get("children");
        for (String child : childrenList) {
            if (child.startsWith("{")) {
                Object temp = new JSONParser().parse(child);
                JSONObject JSONStep = (JSONObject) temp;
                JSONObject JSONStepList = (JSONObject) JSONStep.get("steps");
                parseSteps(JSONStepList);
            } else {
                stepList.add(new Step(child));
            }
        }
        return stepList;
    }

    static Scenario parse(String jsonString) throws ParseException, InvalidScenarioException {
        Object obj = new JSONParser().parse(jsonString);
        JSONObject jo = (JSONObject) obj;

        JSONConverter.validate(jo);

        String title = jo.get("title").toString();
        JSONArray actorsJSON = (JSONArray) jo.get("actors");
        JSONArray systemActorsJSON = (JSONArray) jo.get("systemActors");
        JSONArray stepsJSON = (JSONArray) jo.get("steps");
        ArrayList<String> actorsList = jsonArrayToArrayList(actorsJSON);
        ArrayList<String> systemActorsList = jsonArrayToArrayList(systemActorsJSON);
        ArrayList<String> steps = jsonArrayToArrayList(stepsJSON);
        Scenario scenario = new Scenario(
            title,
            actorsList,
            systemActorsList
        );
        for (String stepString : steps) {
            if (stepString.startsWith("{")) {

                Object temp = new JSONParser().parse(stepString);
                JSONObject JSONStepList = (JSONObject) temp;
                scenario.add(parseSteps(JSONStepList));
            } else {
                Step step = new Step(stepString);
                scenario.add(step);
            }
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
