package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class StepList implements Component {

    private final ArrayList<Component> children;

    public StepList() {
        this.children = new ArrayList<>();
    }

    public List<Component>
    getChildren() {
        return children;
    }

    public void
    addItem(Component item) {
        children.add(item);
    }

    public void
    removeItem(Component item) {
        children.remove(item);
    }

    @Override
    public void
    accept(Visitor visitor) {

    }
}
