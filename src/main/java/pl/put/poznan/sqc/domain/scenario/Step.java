package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.ScenarioJSONParser;
import pl.put.poznan.sqc.domain.visitors.Visitor;

public class Step extends ScenarioJSONParser implements Component {

    private String tekst;

    public Step(String tekst)
    {
        this.tekst = tekst;
    }

    public String getTekst() {
        return tekst;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
