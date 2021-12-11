package pl.put.poznan.sqc.rest;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.logic.InvalidScenarioJSONException;
import pl.put.poznan.sqc.logic.SQC;

/**
 * A controller that gives access to the SQC app functionality
 * via REST API calls.
 */
@RestController
@RequestMapping("/scenario")
public class SQCController {

    /**
     * Logs data during runtime
     */
    private static final Logger logger = LoggerFactory.getLogger(SQCController.class);

    /**
     * Application logic provider
     */
    private SQC app = null;


    /**
     * POST a new scenario.
     * It overrides any existing previous scenarios.
     *
     * @param json description of the scenario as a JSON passed in request body
     * @return request response with a comment and a status code:
     * [CREATED] if successful /
     * [BAD REQUEST] if the JSON impossible to parse (invalid syntax) /
     * [BAD REQUEST] if the JSON does not represent a scenario correctly
     * @see pl.put.poznan.sqc.logic.SQC
     */
    // TODO: 2021-12-10 produces = "application/json"
    @PostMapping(value = "/")
    public ResponseEntity<String>
    postScenario(@RequestBody String json) {
        logger.debug(json);

        try {
            // TODO:    2021-12-10 implement
            this.app = SQC.fromJSON(json);
            return new ResponseEntity<>("Created a new scenario", HttpStatus.CREATED);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Parsing Error", HttpStatus.BAD_REQUEST);
        } catch (InvalidScenarioJSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid Scenario Definition", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * DELETE an existing scenario, if any.
     *
     * @return request response with a comment and a status code:
     * [OK] if deleted /
     * [OK] if none to deleted: none will be left in memory
     */
    @DeleteMapping("/")
    public ResponseEntity<String>
    deleteScenario() {
        if (this.app == null) return new ResponseEntity<>("Already empty", HttpStatus.OK);
        this.app = null;
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
        try {
            int result = this.app.runStepCounter();
            return new ResponseEntity<>(Integer.toString(result), HttpStatus.NOT_IMPLEMENTED);
            //            return new ResponseEntity<String>(Integer.toString(result), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("No scenario to analyse", HttpStatus.NOT_FOUND);
        }
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
        try {
            int result = this.app.runStepCounter();
            return new ResponseEntity<>(Integer.toString(result), HttpStatus.NOT_IMPLEMENTED);
            // return new ResponseEntity<String>(Integer.toString(result), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("No scenario to analyse", HttpStatus.NOT_FOUND);
        }
    }

}