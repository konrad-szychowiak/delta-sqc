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
        String[] splittedText = splitStep(strippedText);
        int actorCount = howManyActorsInStep(splittedText);
        for(int i=0; i<actorCount; i++){
            String actorName = whichActorInStep(splittedText);
            splittedText = changeSplittedStep(actorName, splittedText);
            increment(actorName);

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
    private String[]
    splitStep(String text)
    {
        String stripped = Pattern.compile("(,|:|\\.)").matcher(text).replaceAll("");
        String[] words = stripped.split(" ");
        for(int i=0; i<words.length; i++){
            words[i] = words[i].toLowerCase();
        }
        return words;
    }

    private Integer
    howManyActorsInStep(String[] words) {
        int counter = 0;
        for(int i=0; i<words.length; i++)
        {
            if(actorCounter.containsKey(words[i]))
            {
                counter = counter + 1;
            }
        }
        return counter;
    }

    private String
    whichActorInStep(String[] words) {
        String actor = "";
        int index = 0;
        for (int i=0; i<words.length; i++) {
            if(actorCounter.containsKey(words[i])) {
                actor = words[i];
                break;
            }
        }
        return actor;
    }

    private String[]
    changeSplittedStep(String actor, String[] words){
        for(int i = 0; i< words.length; i++){
            if(words[i] == actor){
                words[i] = null;
                break;
            }
        }
        return words;
    }
    /**
     * Increments a number of times that actor occurred
     * @param actor
     */
    private void
    increment(String actor) {
        int count = actorCounter.get(actor);
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