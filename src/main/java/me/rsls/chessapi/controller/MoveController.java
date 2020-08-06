package me.rsls.chessapi.controller;

import me.rsls.chessapi.model.ClickedFields;
import me.rsls.chessapi.model.Validation;
import me.rsls.chessapi.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/move")
public class MoveController {

    @Autowired
    MoveService moveService;

    @RequestMapping(value = "doMove", method = RequestMethod.POST)
    public ResponseEntity<Validation> doMove(@RequestBody ClickedFields clickedFields) {
        Validation validation = moveService.handleMove(clickedFields);

        return new ResponseEntity<>(validation, HttpStatus.OK);
    }
}
