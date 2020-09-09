package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class CheckService {

    @Autowired
    private FigureService figureService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;


    public void validateCheck(Field sourceField, Field targetField, CheckState checkState) {
        Board board = gameService.getCurrentBoard();

        //reset check state
        this.resetCheckState(checkState);

        processCheckValidation(sourceField, targetField, Color.BLACK);

        if (!checkState.isCheck()) {
            processCheckValidation(sourceField, targetField, Color.WHITE);
        }
    }

    private void resetCheckState(CheckState checkState) {
        checkState.setCheck(false);
        checkState.setCheckColor(null);
    }

    private void processCheckValidation(Field sourceField, Field targetField, Color kingColor) {

        Board board = gameService.getCurrentBoard();

        //execute move, to check the new situation
        moveExecutorService.executeMove(sourceField, targetField);

        //get only enemies of the king
        List<Figure> enemyList = board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> !f.getFigureColor().equals(kingColor))
                .collect(Collectors.toList());

        Figure king = figureService.getKing(kingColor);

        Field kingField = figureService.getFigureField(king);

        enemyList.forEach(figure -> {

            Field figureField = figureService.getFigureField(figure);
            ValidFields figureValidTargetFields = validFieldService.validateFields(figureField, figure);

            if (figureValidTargetFields.getFieldList().get(kingField) != null) {
                board.getCheck().setCheck(true);
                board.getCheck().setCheckColor(kingColor);
            }
        });

        moveExecutorService.revertLastMove();
    }

}
