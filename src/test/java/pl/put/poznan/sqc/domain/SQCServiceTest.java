package pl.put.poznan.sqc.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.domain.errors.MissingScenarioError;
import pl.put.poznan.sqc.domain.scenario.Scenario;
import pl.put.poznan.sqc.domain.scenario.StepList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SQCServiceTest {
    private SQCService service;
    private Scenario scenario;

    @BeforeEach
    void setUp() {
        service = new SQCService();
        scenario = mock(Scenario.class);
        when(scenario.getSteps()).thenReturn(new ArrayList<>());
    }

    @Test
    void doesNotHaveScenario() {
        assertFalse(service.hasScenario());
    }

    @Test
    void hasScenario() {
        service.setScenario(scenario);
        assertTrue(service.hasScenario());
    }


    @Test
    void removeScenario() {
        service.setScenario(scenario);
        assertTrue(service.hasScenario());
        service.removeScenario();
        assertFalse(service.hasScenario());
    }

    @Test
    void getAnyCountForNoScenario() {
        assertFalse(service.hasScenario());
        assertThrows(MissingScenarioError.class, () -> service.getStepCount());
        assertThrows(MissingScenarioError.class, () -> service.getKeywordCount());
    }

    @Test
    void getStepCountForEmptyScenario() {
        Scenario emptyScenario = scenario;
        service.setScenario(emptyScenario);
        assertEquals(0, service.getStepCount());
    }

    @Test
    void getKeywordCountForEmptyScenario() {
        Scenario emptyScenario = scenario;
        service.setScenario(emptyScenario);
        assertEquals(0, service.getKeywordCount());
    }
}