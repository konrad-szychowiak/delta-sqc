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
    // TODO: 2021-12-10 produces = "application/json"
    @PostMapping("")
    public ResponseEntity<String>
    postScenario(@RequestBody String json) {
        logger.debug(json);

        try {
            // TODO: 2021-12-10 implement
            this.service.setScenario(json);
            return new ResponseEntity<>("Created a new scenario", HttpStatus.CREATED);
        }
        catch (ParseException e) {
            String message = "Parsing Error";
            logger.error(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        catch (InvalidScenarioException e) {
            String message = "Invalid Scenario Definition";
            logger.error(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * DELETE an existing scenario, if any.
     *
     * @return request response with a comment and a status code:
     * [OK] if deleted /
     * [NOT FOUND] if none to deleted: none will be left in memory
     */
    @DeleteMapping("")
    public ResponseEntity<String>
    deleteScenario() {
        if (!this.service.hasScenario()) return new ResponseEntity<>("Already empty", HttpStatus.NOT_FOUND);
        this.service.removeScenario();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * GET a total number of steps in the current scenario.
     *
     * @return request response with a comment and a status code:
     * [OK] and a number if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping("/steps")
    public ResponseEntity<String>
    getStepCount() {
        if (!service.hasScenario()) return new ResponseEntity<>("No scenario to analyse", HttpStatus.NOT_FOUND);
        int result = this.service.getStepCount();
        return new ResponseEntity<>(Integer.toString(result), HttpStatus.OK);
    }

    /**
     * GET a total number of keywords in all the steps of the current scenario.
     *
     * @return request response with a comment and a status code:
     * [OK] and a number if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping("/keywords")
    public ResponseEntity<String>
    getKeywordCount() {
        if (!service.hasScenario()) return new ResponseEntity<>("No scenario to analyse", HttpStatus.NOT_FOUND);
        int result = this.service.getKeywordCount();
        return new ResponseEntity<>(Integer.toString(result), HttpStatus.OK);
    }
}