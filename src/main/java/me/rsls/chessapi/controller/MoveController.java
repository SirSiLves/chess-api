package me.rsls.chessapi.controller;

import me.rsls.chessapi.model.ClickedFields;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.BotService;
import me.rsls.chessapi.service.HandleMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/api/move")
public class MoveController {

    @Autowired
    private HandleMoveService handleMoveService;

    @Autowired
    private BotService botService;


    @RequestMapping(value = "doMove", method = RequestMethod.POST)
    public ResponseEntity<Validation> doMove(@RequestBody ClickedFields clickedFields) {

        Validation validation = handleMoveService.handleMove(clickedFields.getSourceField(), clickedFields.getTargetField());

        return new ResponseEntity<>(validation, HttpStatus.OK);
    }

    //TODO GET?

    @RequestMapping(value = "doBotMove", method = RequestMethod.GET)
    public ResponseEntity<Field[]> doBotMove() {

        Field[] sourceAndTarget = botService.executeRandomBotMove();

        return new ResponseEntity<>(sourceAndTarget, HttpStatus.OK);
    }


}
