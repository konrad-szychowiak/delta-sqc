package pl.put.poznan.sqc.domain;

import org.json.simple.parser.ParseException;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;
import pl.put.poznan.sqc.domain.errors.MissingScenarioError;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.visitors.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A service that contains the application's state (a scenario)
 * and provides access to the app's various functionalities (visitors)
 * via public methods.
 *
 * @see Scenario
 * @see StepCounter
 * @see KeywordCounter
 */
public class SQCService {
    /**
     * A reference to a scenario on which all the operations will be done.
     */
    private Scenario scenario = null;

    /**
     * Access the currently stored scenario.
     *
     * @return the Scenario
     * @throws MissingScenarioError if no scenario is stored
     * @see Scenario
     * @see SQCService#setScenario(Scenario)
     * @see SQCService#setScenario(String)
     */
    Scenario
    getScenario()
        throws MissingScenarioError {
        if (this.scenario == null) throw new MissingScenarioError();
        return this.scenario;
    }

    /**
     * Sets a scenario based on a Scenario class.
     *
     * @param scenario the scenario (as a Scenario instance) that will be stored as the application's state.
     * @see Scenario
     * @see SQCService#setScenario(String)
     * @see SQCService#getScenario()
     */
    public void
    setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    /**
     * Sets a scenario based on a String.
     *
     * @param jsonString the scenario (as a stringified JSON) that will be stored as the application's state
     *                   (it will be converted to a Scenario object).
     * @see Scenario
     * @see JSONConverter
     * @see SQCService#setScenario(Scenario)
     * @see SQCService#getScenario()
     */
    public void
    setScenario(String jsonString) throws InvalidScenarioException, ParseException {
        this.scenario = JSONConverter.parse(jsonString);
    }

    /**
     * Tests if there is a scenario loaded.
     *
     * @return true if the SQCService has a scenario set | false
     * @see SQCService#getScenario()
     * @see SQCService#setScenario(Scenario)
     * @see SQCService#setScenario(String)
     */
    public boolean
    hasScenario() {
        return !(this.scenario == null);
    }

    /**
     * Deletes the stored scenario.
     */
    public void
    removeScenario() {
        this.scenario = null;
    }

    /**
     * Runs a StepCounter visitor on the stored scenario.
     *
     * @return the number of steps in the stored scenario.
     * @throws MissingScenarioError if no scenario is stored
     * @see StepCounter
     */
    public int
    getStepCount() throws MissingScenarioError {
        StepCounter counter = new StepCounter();
        this.getScenario().accept(counter);
        return counter.getCount();
    }

    /**
     * Runs a KeywordCounter visitor on the stored scenario.
     *
     * @return the number of keywords in the stored scenario.
     * @throws MissingScenarioError if no scenario is stored
     * @see KeywordCounter
     */
    public int
    getKeywordCount() throws MissingScenarioError {
        KeywordCounter counter = new KeywordCounter();
        this.getScenario().accept(counter);
        return counter.getCount();
    }

    /**
     * Runs a ActorlessAccumulator visitor on the stored scenario.
     *
     * @return a list of step texts where no actor begins the step.
     * @throws MissingScenarioError if no scenario is stored
     * @see ActorlessAccumulator
     */
    public ArrayList<String>
    getActorlessSteps() throws MissingScenarioError {
        ActorlessAccumulator accumulator = new ActorlessAccumulator();
        this.getScenario().accept(accumulator);
        return accumulator.getAccumulator();
    }

    /**
     * Runs a ActorCount visitor on the stored scenario.
     *
     * @return a map of: actor -> number of steps in which they take part
     * @throws MissingScenarioError if no scenario is stored
     * @see ActorCount
     */
    public HashMap<String, Integer>
    getActorCountMap() throws MissingScenarioError {
        ActorCount counter = new ActorCount();
        this.getScenario().accept(counter);
        return counter.getActorCounter();
    }

    /**
     * Runs a ActorCount visitor on the stored scenario.
     *
     * @return a map of: actor -> number of steps in which they take part
     * @throws MissingScenarioError if no scenario is stored
     * @see ActorCount
     */
    public ArrayList<String>
    getLonelyActorsList() throws MissingScenarioError {
        var counter = new LonelyActorAccumulator();
        this.getScenario().accept(counter);
        return counter.getLonely();
    }

    /**
     * Runs a ActorlessAccumulator visitor on the stored scenario.
     *
     * @return a list of step texts where no actor begins the step.
     * @throws MissingScenarioError if no scenario is stored
     * @see ActorlessAccumulator
     */
    public String
    getToDepth(int depth) throws MissingScenarioError {
        var levelCounter = new LevelCounter(depth);
        levelCounter.visit(this.getScenario(), depth);
        var cut = levelCounter.getCuttedScenario();
        return new ScenarioConverter(cut).getJsonScenario();
    }
}