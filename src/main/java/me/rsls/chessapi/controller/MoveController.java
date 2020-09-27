package me.rsls.chessapi.controller;

import me.rsls.chessapi.model.ClickedFields;
import me.rsls.chessapi.model.SelectedFigure;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.BotService;
import me.rsls.chessapi.service.HandleMoveService;
import me.rsls.chessapi.service.PawnPromotionService;
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
    private HandleMoveService handleMoveService;

    @Autowired
    private BotService botService;

    @Autowired
    private PawnPromotionService pawnPromotionService;


    @RequestMapping(value = "doMove", method = RequestMethod.POST)
    public ResponseEntity<Validation> doMove(@RequestBody ClickedFields clickedFields) {

        Validation validation = handleMoveService.handleMove(clickedFields.getSourceField(), clickedFields.getTargetField());
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }

    //TODO GET?

    @RequestMapping(value = "doBotMove", method = RequestMethod.GET)
    public ResponseEntity<String> doBotMove() {
        botService.executeRandomBotMove();
        return new ResponseEntity<>("Bot move executed.", HttpStatus.OK);
    }


    @RequestMapping(value = "doPawnPromotion", method = RequestMethod.POST)
    public ResponseEntity<String> doPawnPromotion(@RequestBody SelectedFigure selectedFigure) {

        pawnPromotionService.changePawn(selectedFigure);
        return new ResponseEntity<>("Figure success fully changed", HttpStatus.OK);
    }



}
