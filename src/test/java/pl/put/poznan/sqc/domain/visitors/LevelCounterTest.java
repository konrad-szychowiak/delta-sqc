package pl.put.poznan.sqc.domain.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.scenario.Component;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.Step;
import pl.put.poznan.sqc.domain.scenario.StepList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

/**
 * UC: Jako analityk mogę uzyskać scenariusz zawierający pod-scenariusze tylko do określonego poziomu,
 * aby zaprezentować uproszczoną wersję wymagań.
 */
class LevelCounterTest {
    private Scenario scenario;

    /**
     * Wejście stanowi struktura scenariusza oraz liczba określająca poziom
     */
    @BeforeEach
    void
    setup()
    {
        ArrayList<String> batman = new ArrayList<>();
        ArrayList<String> alfred = new ArrayList<>();
        batman.add("Batman");
        alfred.add("Alfred");
        scenario = new Scenario("", batman, alfred);

        Step step = new Step("IF: Bibliotekarz pragnie dodać egzemplarze książki");
        scenario.add(step);

        StepList list = new StepList(new Step(""));
        list.add(new Step("IF world say hello!"));
        list.add(new Step("ELSE don't say anything"));
        list.add(new Step("FOR EACH hello say greetings"));
        scenario.add(list);
    }

    /**
     * Przy poziomie = 1 zwracany jest tylko scenariusz najwyższego poziomu
     */
    @Test
    void
    level1()
    {
        var level2Visitor = new LevelCounter(1);

        scenario.accept(level2Visitor);

        Scenario cuttedScenario = level2Visitor.getCuttedScenario();
        ArrayList<Component> extractedSteps = cuttedScenario.getSteps();
        for(Component step : extractedSteps)
        {
            if(step instanceof Step) {
                assertThat(((Step) step).getText()).isEqualTo("IF: Bibliotekarz pragnie dodać egzemplarze książki");
            }

        }
    }

    /**
     * Przy poziomach > 1 zwracane są scenariusze do danego poziomu włącznie (np. poziom = 2 oznacza scenariusz na najwyższym poziomie oraz jego bezpośrednie pod-scenariusze)
     */
    /**
     * Przy poziomie = 1 zwracany jest tylko scenariusz najwyższego poziomu
     */
    @Test
    void
    level2()
    {
        var level2Visitor = new LevelCounter(1);

        scenario.accept(level2Visitor);

        Scenario cuttedScenario = level2Visitor.getCuttedScenario();
        ArrayList<Component> extractedSteps = cuttedScenario.getSteps();
        for(Component step : extractedSteps)
        {
            if(step instanceof Step) {
                assertThat(((Step) step).getText()).isEqualTo("IF: Bibliotekarz pragnie dodać egzemplarze książki");
            }
            else if(step instanceof StepList)
            {
                StepList cuttedStepList = (StepList) step;
               assertThat(cuttedStepList.getMainStep().getText()).isEqualTo("");
               ArrayList<Component> childrenSteps = cuttedStepList.getChildren();
               assertThat(((Step)childrenSteps.get(0)).getText()).isEqualTo("IF world say hello!");
               assertThat(((Step)childrenSteps.get(1)).getText()).isEqualTo("ELSE don't say anything");
               assertThat(((Step)childrenSteps.get(2)).getText()).isEqualTo("FOR EACH hello say greetings");
            }
        }
    }

}