package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class StepList implements Component {

    // TODO: 2021-12-18 make it an ArrayList  
    private final List<Component> children;

    public StepList() {
        this.children = new ArrayList<>();
    }

    public List<Component>
    getChildren() {
        return children;
    }

    public void
    add(Component item) {
        // TODO: 2021-12-18 add the item to the children
    }

    public void
    remove(Component item) {
        // TODO: 2021-12-18 remove the item from the children
    }

    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
        for (Component child : children) {
            child.accept(visitor);
        }
    }
}
