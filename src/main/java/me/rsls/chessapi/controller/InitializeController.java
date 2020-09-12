package me.rsls.chessapi.controller;

import me.rsls.chessapi.service.InitializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/initialize")
public class InitializeController {

    @Autowired
    private InitializeService initializeService;

    @RequestMapping(value = "createGame", method = RequestMethod.GET)
    public ResponseEntity<String> createGame() {
        initializeService.initializeGame();
        return new ResponseEntity<>("Game successfully initialized.", HttpStatus.OK);
    }
}
