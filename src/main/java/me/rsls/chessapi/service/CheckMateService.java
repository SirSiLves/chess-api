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

        for(Figure g : guardiens){
            Field guardSourceField = figureService.getFigureField(g);
            ValidFields validGuardFields = validFieldService.validateFields(guardSourceField, g);

            //check if a mate can prevent the check state
            for(Field guardTargetField : validGuardFields.getFieldList().keySet()){
                CheckState checkState = new CheckState();

                checkService.validateCheck(guardSourceField, guardTargetField, checkState);
                // a mate can prevent the check
                if(!checkState.isCheck()){
                    canProtect = true;
                }
            }
        }

        if(!canProtect) {
            currentCheckState.setCheckMate(true);
        }

        moveExecutorService.revertLastMove();
    }
}
