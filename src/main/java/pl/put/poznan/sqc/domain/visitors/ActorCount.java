package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.HashMap;

public class ActorCount implements Visitor {

    /**
     * Accumulates all actors in and number of their occurrences in Scenario
     */
    HashMap<String, Integer> actorCounter = new HashMap<String, Integer>();

    /**
     * Reads all actors and system actors from a scenario.
     *
     * <p> Actor names are transformed to lower case and stored in a hashmap
     * Later, they are compared to the first word in a step. When they match, actor's count will increase by 1.</p>
     * @param scenario Scenario which will be analysed
     * @see Scenario#getActors()
     * @see Scenario#getSystemActors()
     * @see ActorlessAccumulator#visit(Step)
     */
    @Override
    public void visit(Scenario scenario) {
        for (var actor : scenario.getActors())
            actorCounter.put(actor.toLowerCase(), 0);
        for (var actor : scenario.getSystemActors())
            actorCounter.put(actor.toLowerCase(), 0);
    }

    /**
     * Checks if startsWithAnyActor detected actor in step.
     * Gets which actor was detected from whichActorStartsWith
     * Increments actor occurrences count
     *
     * @param step Step which will be analysed
     */
    @Override
    public void visit(Step step) {
        String originalText = step.getText();
        String strippedText = stripKeywords(originalText);
        if(startsWithAnyActor(strippedText)) {
            String startingActor = whichActorStartsWith(strippedText);
            Integer actorOccurrences = actorCounter.get(startingActor);
            increment(startingActor, actorOccurrences);
        }
    }

    @Override
    public void
    visit(StepList list) {}

    /**
     * Checks whether the step starts with any actor in scenario
     * @param text
     * @returns true or false
     * @see Step
     */
    private boolean
    startsWithAnyActor(String text) {
        String potentialActor = text.split("\\s", 2)[0].toLowerCase();
        for (String actor : actorCounter.keySet()) {
            if (actor.equals(potentialActor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks which actor exactly the steps starts with
     * @param text
     * @returns name of the actor
     */
    private String
    whichActorStartsWith(String text) {
        String whichActor = text.split("\\s", 2)[0].toLowerCase();
        return whichActor;
    }

    /**
     * Increments a number of times that actor occurred
     * @param actor
     * @param count
     */
    private void
    increment(String actor, Integer count) {
        actorCounter.replace(actor, count + 1);
    }


    /**
     * Strips keywords from the beginning of a given string.
     *
     * <p>Supported keywords are
     * <code>IF</code>,
     * <code>ELSE</code>,
     * <code>FOR EACH</code>.
     * </p>
     *
     * @param original
     * @returns stripped text from step
     */
    public String
    stripKeywords(String original) {
        String stripped = Pattern.compile("^(IF|ELSE|FOR EACH):?\\s").matcher(original).replaceFirst("");

        return stripped;
    }

    public HashMap<String, Integer>
    getActorCounter() {
        return actorCounter;
    }
}