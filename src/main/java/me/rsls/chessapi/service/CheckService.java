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
    private CheckMateService checkMateService;

    @Autowired
    private MoveExecutorService moveExecutorService;


    public CheckState validateCheck(Field sourceField, Field targetField) {
        Board board = gameService.getCurrentBoard();

        //reset check state
        CheckState checkState = board.getCheck();
        this.resetCheckState(checkState);

        processCheckValidation(sourceField, targetField, Color.BLACK);

        if (!checkState.isCheck()) {
            processCheckValidation(sourceField, targetField, Color.WHITE);
        }

        return checkState;
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

        //get king
        Figure king = board.getFigureArrayList().stream()
                .filter(f -> f.getFigureType().equals(FigureType.KING))
                .filter(f -> f.getFigureColor().equals(kingColor))
                .findFirst().get();

        Field kingField = figureService.getFieldWithFigure(king);

        enemyList.forEach(figure -> {

            Field figureField = figureService.getFieldWithFigure(figure);
            ValidFields figureValidTargetFields = validFieldService.validateFields(figureField, figure);

            if (figureValidTargetFields.getFieldList().get(kingField).isState()) {
                board.getCheck().setCheck(true);
                board.getCheck().setCheckColor(kingColor);

                checkMateService.validateCheckMate();
            }
        });

        moveExecutorService.revertLastMove();
    }

}
