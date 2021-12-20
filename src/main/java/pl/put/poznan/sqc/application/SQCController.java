package pl.put.poznan.sqc.application;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.domain.SQCService;
import pl.put.poznan.sqc.domain.errors.InvalidScenarioException;

/**
 * A controller that gives access to the SQC app functionality
 * via REST API calls.
 */
@RestController
@RequestMapping("/scenario")
public class SQCController {
    private static final Logger logger = LoggerFactory.getLogger(SQCController.class);
    private final SQCService service = new SQCService();

    /**
     * POST a new scenario.
     * It overrides any existing previous scenarios.
     *
     * @param json description of the scenario as a JSON passed in request body
     * @return request response with a comment and a status code:
     * [CREATED] if successful /
     * [BAD REQUEST] if the JSON impossible to parse (invalid syntax) /
     * [BAD REQUEST] if the JSON does not represent a scenario correctly
     * @see SQCService
     */
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<String>
    postScenario(@RequestBody String json) {
        logger.debug(json);

        try {
            this.service.setScenario(json);
            return new ResponseEntity<>(
                "{\"message\":\"Created a new scenario\"}",
                HttpStatus.CREATED
            );
        }
        catch (ParseException e) {
            String message = "Parsing Error";
            logger.error(message);

            return new ResponseEntity<>(
                "{\"message\":\"" + message + "\"}",
                HttpStatus.BAD_REQUEST
            );
        }
        catch (InvalidScenarioException e) {
            String message = "Invalid Scenario Definition";
            logger.error(message);
            return new ResponseEntity<>(
                "{\"message\":\"" + message + "\"}",
                HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * DELETE an existing scenario, if any.
     *
     * @return request response with a comment and a status code:
     * [OK] if deleted /
     * [NOT FOUND] if none to deleted: none will be left in memory
     */
    @DeleteMapping(value = "", produces = "application/json")
    public ResponseEntity<String>
    deleteScenario() {
        if (!this.service.hasScenario()) return new ResponseEntity<>(
            "{\"message\":\"Already empty\"}",
            HttpStatus.NOT_FOUND
        );
        this.service.removeScenario();
        return new ResponseEntity<>(
            "{\"message\":\"OK\"}",
            HttpStatus.OK
        );
    }

    /**
     * GET a total number of steps in the current scenario.
     *
     * @return request response with a comment and a status code:
     * [OK] and a number if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping(value = "/steps", produces = "application/json")
    public ResponseEntity<String>
    getStepCount() {
        if (!service.hasScenario()) return new ResponseEntity<>(
            "{\"message\":\"No scenario to analyse\"}",
            HttpStatus.NOT_FOUND
        );
        int result = this.service.getStepCount();
        return new ResponseEntity<>(
            "{\"count\":" + result + "}",
            HttpStatus.OK
        );
    }

    /**
     * GET a total number of keywords in all the steps of the current scenario.
     *
     * @return request response with a comment and a status code:
     * [OK] and a number if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping(value = "/keywords", produces = "application/json")
    public ResponseEntity<String>
    getKeywordCount() {
        if (!service.hasScenario()) return new ResponseEntity<>(
            "{\"message\":\"No scenario to analyse\"}",
            HttpStatus.NOT_FOUND
        );
        int result = this.service.getKeywordCount();
        return new ResponseEntity<>(
            "{\"count\":" + result + "}",
            HttpStatus.OK
        );
    }
}