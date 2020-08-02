package me.rsls.chessapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/move")
public class MoveController {

    @RequestMapping(value = "doMove", method = RequestMethod.POST)
    public ResponseEntity<String> doMove(@RequestBody String movedFields) {
        return new ResponseEntity<>("MOVE DONE! " + movedFields, HttpStatus.OK);
    }
}
