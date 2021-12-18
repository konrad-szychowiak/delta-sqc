package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class StepList implements Component {

    // TODO: 2021-12-18 make it an ArrayList  
    private List<Component> children;

    public StepList() {
        this.children = new ArrayList<>();
    }

    public List<Component>
    getChildren() {
        return children;
    }

    public void
    add(Step step) {
        // TODO: 2021-12-18 add the step to the children list
    }

    public void
    remove(Step step) {
        // TODO: 2021-12-18 remove the step from the list
    }

    @Override
    public void
    accept(Visitor visitor) {

    }
}
