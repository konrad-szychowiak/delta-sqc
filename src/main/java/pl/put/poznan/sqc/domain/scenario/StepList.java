package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;

public class StepList implements Component {

    private final ArrayList<Component> children;

    public StepList() {
        this.children = new ArrayList<>();
    }

    public ArrayList<Component>
    getChildren() {
        return children;
    }

    public void
    add(Component item) {
        children.add(item);
    }

    public void
    remove(Component item) {
        children.remove(item);
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
