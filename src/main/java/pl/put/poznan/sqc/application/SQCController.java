package pl.put.poznan.sqc.application;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.domain.SQCService;
import pl.put.poznan.sqc.domain.translation.HashMapJSON;

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
     * [BAD REQUEST] if a parsing error occurred
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
        catch (Exception e) {
            String message = "Parsing Error";
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
        logger.debug("Delete scenario!");
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
        logger.debug("Requested number of steps in the scenario...");
        if (!service.hasScenario()) return new ResponseEntity<>(
            "{\"message\":\"No scenario to analyse\"}",
            HttpStatus.NOT_FOUND
        );
        int result = this.service.getStepCount();
        logger.debug("...".concat(String.valueOf(result)));
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
        logger.debug("Requested the number of keywords used throughout all the steps...");
        if (!service.hasScenario()) return new ResponseEntity<>(
            "{\"message\":\"No scenario to analyse\"}",
            HttpStatus.NOT_FOUND
        );
        int result = this.service.getKeywordCount();
        logger.debug("...".concat(String.valueOf(result)));
        return new ResponseEntity<>(
            "{\"count\":" + result + "}",
            HttpStatus.OK
        );
    }

    /**
     * GET a list of step texts where no actor begins the step.
     *
     * @return request response with a comment and a status code:
     * [OK] and a list if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping(value = "/actorless", produces = "application/json")
    public ResponseEntity<String>
    getActorlessSteps() {
        logger.debug("GET a list of step texts where no actor begins the step");
        if (!service.hasScenario())
        {
            String message = "No scenario to analyse";
            logger.error(message);
            return new ResponseEntity<>(
                "{\"message\":\""+message+"\"}",
                HttpStatus.NOT_FOUND
            );
        }
        var result = this.service.getActorlessSteps();
        var message = JSONArray.toJSONString(result);
        logger.debug(message);

        return new ResponseEntity<>(
            "{\"steps\":" + message + "}",
            HttpStatus.OK
        );
    }
    /**
     * GET a list of actors that do not appear in any steps.
     *
     * @return response with a list of actors +
     * [OK] if successful /
     * [NOT FOUND] if no scenario
     */
    @GetMapping(value = "/actors/lonely", produces = "application/json")
    public ResponseEntity<String>
    getLonelyActors() {
        logger.debug("GET a list of actors that do not appear in any steps");
        if (!service.hasScenario())
        {
            String message = "No scenario to analyse";
            logger.error(message);
            return new ResponseEntity<>(
                "{\"message\":\""+message+"\"}",
                HttpStatus.NOT_FOUND
            );
        }
        var result = this.service.getLonelyActorsList();
        var message = JSONArray.toJSONString(result);
        logger.debug(message);

        return new ResponseEntity<>(
            "{\"actors\":{\"lonely\":" + message + "}}",
            HttpStatus.OK
        );
    }

    /**
     * GET a map of actors and the number of steps they partake in.
     *
     * @return response as a json object + a status code:
     * <code>200 OK</code> and a list if successful /
     * <code>404 NOT FOUND</code> if no scenario
     */
    @GetMapping(value = "/actors/count", produces = "application/json")
    public ResponseEntity<String>
    getActorCountMap()
    {
        logger.debug("GET a map of actors and the number of steps they partake in");
        if (!service.hasScenario())
        {
            String message = "No scenario to analyse";
            logger.error(message);
            return new ResponseEntity<>(
                "{\"message\":\""+message+"\"}",
                HttpStatus.NOT_FOUND
            );
        }
        var result = service.getActorCountMap();
        var message = new HashMapJSON<String, Integer>(result).stringify();
        return new ResponseEntity<>(
            "{\"actors\":" + message + "}",
            HttpStatus.OK
        );
    }
}