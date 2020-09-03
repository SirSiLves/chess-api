package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CheckService {

    //TODO - Ranking System gegen BOT. Kein player vs player system

    @Autowired
    private FigureService figureService;


    public void validateCheck(Board board) {

//        board.getCheck().setCheck(true);

//        if (board.getFieldFromMatrix("a", 5).getFigure() != null &&
//                board.getFieldFromMatrix("a", 5).getFigure().getFigureType().equals(FigureType.QUEEN)) {
//            board.getCheck().setCheck(true);
//        }


        CheckState currentCheckState = board.getCheck();

        Color lastPlayed = board.getLastPlayed();

        List<Figure> figureList = board.getFigureArrayList();
        Figure king = figureList.stream()
                .filter(f -> f.getFigureType().equals(FigureType.KING) && !f.getFigureColor().equals(lastPlayed))
                .findAny()
                .get();

        Field kingField = figureService.getFieldWithFigure(board, king);


        board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> !f.getFigureColor().equals(king.getFigureColor()))
                .forEach(f -> {
            Field currentField = figureService.getFieldWithFigure(board, f);
            if (isCheck(board, currentField, kingField)) {
                currentCheckState.setCheck(true);
            }
        });




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

//        System.out.println(validation);

        return validation.isState();
    }


}
