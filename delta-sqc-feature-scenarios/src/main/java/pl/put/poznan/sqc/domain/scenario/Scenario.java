package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.List;

// TODO: 2021-12-10
//  the whole class
public class Scenario implements Visitable {
    private String title;
    private List<String> actors;
    private List<String> systemActors;
    private StepList steps;
    public Scenario( String title, List<String> actors, List<String> systemActors,StepList steps)
    {
        this.title = title;
        this.actors=actors;
        this.systemActors = systemActors;
        this.steps=steps;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getSystemActors() {
        return systemActors;
    }

    public StepList getSteps() {
        return steps;
    }

    public void accept(Visitor visitor) {}


    }
