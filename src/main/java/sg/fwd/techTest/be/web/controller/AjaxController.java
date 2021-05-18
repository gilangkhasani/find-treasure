package sg.fwd.techTest.be.web.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import sg.fwd.techTest.be.web.jsonview.Views;
import sg.fwd.techTest.be.web.model.AjaxResponseBody;
import sg.fwd.techTest.be.web.model.Command;
import sg.fwd.techTest.be.web.model.Grid;
import sg.fwd.techTest.be.web.model.Point;
import sg.fwd.techTest.be.web.service.Initiator;


/*
Author : Gilang Permadi Khasani
Description : Ajax Controller for response each data & start the game
Version : 0.1
Last Update : 18-05-2021
 */
@RestController
public class AjaxController {
    Initiator initiator = new Initiator();
    Point res;

    // @ResponseBody, not necessary, since class is annotated with @RestController
    // @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
    // @JsonView(Views.Public.class) - Optional, limited the json data display to client.
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search/api/initGame")
    public AjaxResponseBody getSearchResultViaAjax(@RequestBody Grid search) {

        AjaxResponseBody result = new AjaxResponseBody();

        if (isNotEmptyGrid(search)) {
            res = initiator.initGame(search.getGridSize());
            result.setCode("200");
            result.setMsg("Game Started !!!");
            result.setResult(res);

        } else {
            result.setCode("400");
            result.setMsg("Grid is empty!");
        }

        //AjaxResponseBody will be converted into json format and send back to client.
        return result;

    }

    private boolean isNotEmptyGrid(Grid search) {

        boolean valid = true;

        if (search == null) {
            valid = false;
        }

        if (search.getGridSize() == null) {
            valid = false;
        }

        return valid;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search/api/command")
    public AjaxResponseBody saveCommand(@RequestBody Command search) {

        AjaxResponseBody result = new AjaxResponseBody();
        res = initiator.processCommand(search.getCommand());
        result.setCode("200");
        result.setMsg("Process Command");
        result.setResult(res);

        //AjaxResponseBody will be converted into json format and send back to client.
        return result;

    }

}
