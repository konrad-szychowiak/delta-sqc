package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

import java.util.List;

public class StepList implements Component {

    private List<Component> children;

    public StepList(List<Component> children)
    {
        this.children =  children;
    }


    public List<Component> getChildren() {
        return children;
    }

    public void setChildren(List<Component> children) {
        this.children = children;
    }

    public void add()
    {

    }

    public void remove()
    {

    }
    @Override
    public void accept(Visitor visitor) {

    }
}
