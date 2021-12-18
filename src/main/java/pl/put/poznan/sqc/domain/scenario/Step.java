package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

public class Step implements Component {

    private final String text;

    public Step(String text) {
        this.text = text;
    }

    public String
    getText() {
        return text;
    }

    @Override
    public void
    accept(Visitor visitor) {
        visitor.visit(this);
    }
}
