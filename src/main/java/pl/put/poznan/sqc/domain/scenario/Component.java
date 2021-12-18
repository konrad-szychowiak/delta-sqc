package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

public interface Component extends Visitable {
    @Override
    void accept(Visitor visitor);
}
