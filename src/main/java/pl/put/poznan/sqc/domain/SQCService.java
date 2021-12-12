package pl.put.poznan.sqc.domain;

import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.errors.MissingScenarioError;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.visitors.KeywordCounter;
import pl.put.poznan.sqc.domain.visitors.StepCounter;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SQCService {
    // TODO: 2021-12-10 Scenario
    private Scenario scenario = null;

    private Scenario
    getScenario()
        throws MissingScenarioError {
        if (this.scenario == null) throw new MissingScenarioError();
        return this.scenario;
    }

    public void
    setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void
    setScenario(String jsonString) throws InvalidScenarioException, ParseException {
        // TODO: 2021-12-12 parse the json object
        this.scenario = ScenarioJSONParser.parse(jsonString);
    }

    public boolean
    hasScenario() {
        return !(this.scenario == null);
    }

    public void
    removeScenario() {
        this.scenario = null;
    }

    public int
    getStepCount() throws MissingScenarioError {
        StepCounter counter = new StepCounter();
        this.getScenario().accept(counter);
        return counter.getCount();
    }

    public int
    getKeywordCount() throws MissingScenarioError {
        KeywordCounter counter = new KeywordCounter();
        this.getScenario().accept(counter);
        return counter.getCount();
    }
}