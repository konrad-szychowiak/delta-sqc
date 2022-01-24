package pl.put.poznan.sqc.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.*;
import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

public class ScenarioConverter {
    Scenario  scenario;
    JSONObject jsonScenario;
    public ScenarioConverter(Scenario scenario)
    {
        this.scenario = scenario;
        this.jsonScenario = new JSONObject();
        jsonScenario.put("title", scenario.getTitle());
        jsonScenario.put("actors", scenario.getActors());
        jsonScenario.put("systemActors", scenario.getSystemActors());
        JSONArray stepsJSONArray = scenarioStepsToJSON(new JSONArray(),scenario.getSteps());
        jsonScenario.put("steps", stepsJSONArray);
    }


    private JSONArray scenarioStepsToJSON(JSONArray jsonSteps, ArrayList<Component> steps)
    {
        for(Component step : steps)
        {
            if (step instanceof StepList) {
                JSONObject nestedStep = new JSONObject();
                nestedStep.put("text", ((StepList)step).getMainStep().getText());
                JSONArray nestedStepChildren = scenarioStepsToJSON( new JSONArray() ,((StepList)step).getChildren());
                nestedStep.put("children", nestedStepChildren);
                jsonSteps.add(nestedStep);
            }
            else if (step instanceof Step)
            {
                jsonSteps.add(((Step) step).getText());
            }
        }
        return jsonSteps;
    }


    public String getJsonScenario() {
        return jsonScenario.toString();
    }
}
