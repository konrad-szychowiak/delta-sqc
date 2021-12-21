package pl.put.poznan.sqc.domain.visitors;

import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActorlessAccumulator implements Visitor {
    /**
     * Accumulates Steps with a certain condition
     */
    private final ArrayList<String> accumulator = new ArrayList<>();

    /**
     * All actors in a scenario, as lowercase strings.
     *
     * @see Scenario#getActors()
     */
    private final ArrayList<String> allActors = new ArrayList<>();

    /**
     * Reads all actors and system actors from a scenario.
     *
     * <p>Actor names are transformed to lower case and stored in a list.
     * Later, they are compared against the first word in a step (also in lower case).</p>
     *
     * @param scenario Scenario which will be analysed
     * @see Scenario#getActors()
     * @see Scenario#getSystemActors()
     * @see ActorlessAccumulator#visit(Step)
     */
    @Override
    public void visit(Scenario scenario) {
        for (var actor : scenario.getActors())
            allActors.add(actor.toLowerCase());
        for (var actor : scenario.getSystemActors())
            allActors.add(actor.toLowerCase());
    }

    /**
     * Checks if startsWithAnyActor detected actor in step.
     *
     * @param step Step which will be analysed
     */
    @Override
    public void visit(Step step) {
        String originalText = step.getText();
        String strippedText = stripKeywords(originalText);
        if (!startsWithAnyActor(strippedText)) {
            accumulate(originalText);
        }
    }

    @Override
    public void
    visit(StepList list) {}

    /**
     * Add the text of a step that doesn't include any actors to the accumulator.
     *
     * @param text text which will be stored in the accumulator
     */
    private void
    accumulate(String text) {
        accumulator.add(text);
    }

    public ArrayList<String>
    getAccumulator() {
        return accumulator;
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
     * @return
     */
    public String
    stripKeywords(String original) {
        String stripped = Pattern.compile("^(IF|ELSE|FOR EACH):?\\s").matcher(original).replaceFirst("");

        return stripped;
    }

    /**
     * Checks whether text of the Step starts with an actor excluding keywords
     *
     * @param text
     * @returns true or false
     * @see Step
     */
    private boolean
    startsWithAnyActor(String text) {
        String potentialActor = text.split("\\s", 2)[0].toLowerCase();
        for (String actor : allActors) {
            if (actor.equals(potentialActor)) {
                return true;
            }
        }
        return false;
    }

}
