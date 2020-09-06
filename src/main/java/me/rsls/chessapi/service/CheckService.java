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


    public CheckState validateCheck(Board board, Field sourceField, Field targetField) {
        //reset check state
        CheckState checkState = board.getCheck();
        this.resetCheckState(checkState);

        processCheckValidation(board, sourceField, targetField, Color.BLACK);

        if(!checkState.isCheck()){
            processCheckValidation(board, sourceField, targetField, Color.WHITE);
        }

        return checkState;
    }

    private void resetCheckState(CheckState checkState){
        checkState.setCheck(false);
        checkState.setCheckColor(null);
    }

    private void processCheckValidation(Board board, Field sourceField, Field targetField, Color kingColor) {

        //execute move, to check the new situation
        Figure movedFigure = sourceField.getFigure();
        Figure killedFigure = targetField.getFigure();
        this.preExecuteMove(sourceField, targetField, movedFigure, killedFigure);

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

        Field kingField = figureService.getFieldWithFigure(board, king);

        enemyList.forEach(figure -> {

            Field figureField = figureService.getFieldWithFigure(board, figure);
            ValidFields figureValidTargetFields = validFieldService.validateFields(board, figureField, figure);

            if (figureValidTargetFields.getFieldList().get(kingField).isState()) {
                board.getCheck().setCheck(true);
                board.getCheck().setCheckColor(kingColor);
            }
        });

        this.revertExecuteMove(sourceField, targetField, movedFigure, killedFigure);
    }

    private void preExecuteMove(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure) {
        //set eliminated figure
        if (killedFigure != null) killedFigure.setAlive(false);

        //set moved figure
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);
    }

    private void revertExecuteMove(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure) {
        //set eliminated figure
        if (killedFigure != null) killedFigure.setAlive(true);

        //set moved figure
        targetField.setFigure(killedFigure);
        sourceField.setFigure(movedFigure);
    }


}
