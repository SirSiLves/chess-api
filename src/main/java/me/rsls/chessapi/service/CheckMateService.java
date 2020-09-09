package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckMateService {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private ValidFieldService validFieldService;


    public void validateCheckMate(Field sourceField, Field targetField) {
        Board board = gameService.getCurrentBoard();
        CheckState currentCheckState = board.getCheck();

        boolean canProtect = false;

        Figure king = figureService.getKing(currentCheckState.getCheckColor());
        Field kingField = figureService.getFigureField(king);

        //pre execute move
        moveExecutorService.executeMove(sourceField, targetField);

        ValidFields kingValidFields = validFieldService.validateFields(kingField, king);

        //get protectors of the king
        List<Figure> guardiens = board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> f.getFigureColor().equals(king.getFigureColor()))
                .collect(Collectors.toList());

        guardiens.forEach(g -> {
            Field guardSourceField = figureService.getFigureField(g);
            ValidFields validGuardFields = validFieldService.validateFields(guardSourceField, g);

            //check if a mate can prevent the check state
            validGuardFields.getFieldList().keySet().forEach(guardTargetField -> {
                CheckState checkState = new CheckState();

                checkService.validateCheck(guardSourceField, guardTargetField, checkState);
                // a mate can prevent the check
                if(!checkState.isCheck()){
                    canProtect = true;
                }
            });
        });

        //event auslösen -> subscribor prüfen... haben wir schachmatt??
        //aop -> aspekt orientierte programm


//    player.startTurn();
//        if (player.isInCheck()
//        if(player.king.hasNoLegalMoves() && player.cannotProtectKing())
//            game.checkMate(player)


        moveExecutorService.revertLastMove();

    }
}
