package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

public interface Visitable {
    void accept(Visitor visitor);
}
