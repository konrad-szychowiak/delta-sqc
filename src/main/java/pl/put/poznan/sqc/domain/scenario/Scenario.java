package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements Visitable {
    private final String title;
    private final ArrayList<String> actors;
    private final ArrayList<String> systemActors;
    private final StepList steps;

    public
    Scenario(String title, ArrayList<String> actors, ArrayList<String> systemActors, StepList steps) {
        this.title = title;
        this.actors = actors;
        this.systemActors = systemActors;
        this.steps = steps;
    }

    public String
    getTitle() {
        return title;
    }

    public List<String>
    getActors() {
        return actors;
    }

    public List<String>
    getSystemActors() {
        return systemActors;
    }

    public StepList
    getSteps() {
        return steps;
    }

    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
        steps.accept(visitor);
    }
}
