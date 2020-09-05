package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CheckService {

    @Autowired
    private FigureService figureService;


    public void validateCheck(Board board, Field sourceField, Field targetField) {
        //execute move, to check the new situation
        Figure movedFigure = sourceField.getFigure();
        Figure killedFigure = targetField.getFigure();
        this.preExecuteMove(sourceField, targetField, movedFigure, killedFigure);

        //reset last state
        board.getCheck().setCheck(false);
        CheckState currentCheckState = board.getCheck();

        Color lastPlayed = board.getLastPlayed();

        ArrayList<Figure> figureArrayList = board.getFigureArrayList();
        Figure king = figureArrayList.stream()
                .filter(f -> f.getFigureType().equals(FigureType.KING) && !f.getFigureColor().equals(lastPlayed))
                .findAny()
                .get();

        Field kingField = figureService.getFieldWithFigure(board, king);

        figureArrayList.stream()
                .filter(Figure::isAlive)
                .filter(f -> !f.getFigureColor().equals(king.getFigureColor()))
                .forEach(f -> {

            Field currentField = figureService.getFieldWithFigure(board, f);
            if (isCheck(board, currentField, kingField)) {
                currentCheckState.setCheck(true);
            }
        });

        //no possible move found -> its check mate
        if(currentCheckState.isCheck()){
            currentCheckState.setCheckMate(true);
        }

        this.revertExecuteMove(sourceField, targetField, movedFigure, killedFigure);
    }


    public Boolean isCheck(Board board, Field sourceField, Field targetField) {

        Validation validation = new Validation(null);

        switch (sourceField.getFigure().getFigureType()) {
            case PAWN -> validation = new Validation(new ValidatePawn(board, sourceField, targetField));
            case ROOK -> validation = new Validation(new ValidateRook(board, sourceField, targetField));
            case BISHOP -> validation = new Validation(new ValidateBishop(board, sourceField, targetField));
            case QUEEN -> validation = new Validation(new ValidateQueen(board, sourceField, targetField));
            case KNIGHT -> validation = new Validation(new ValidateKnight(board, sourceField, targetField));
            case KING -> validation = new Validation(new ValidateKing(board, sourceField, targetField));
        }

        validation.executeValidation();

        return validation.isState();
    }


    private void preExecuteMove(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure) {
        //set eliminated figure
        if(killedFigure != null) killedFigure.setAlive(false);

        //set moved figure
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);
    }

    private void revertExecuteMove(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure) {
        //set eliminated figure
        if(killedFigure != null) killedFigure.setAlive(true);

        //set moved figure
        targetField.setFigure(killedFigure);
        sourceField.setFigure(movedFigure);
    }

}
