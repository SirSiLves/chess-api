package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ValidFieldService {

    @Autowired
    private GameService gameService;


    public ValidFields validateFields(Field sourceField, Figure movedFigure) {

        Board board = gameService.getCurrentBoard();

        ValidFields validFields = new ValidFields();

        for (HashMap<Integer, Field> column : board.getFieldMatrix().values()) {
            for (Field field : column.values()) {
                Validation validation = this.isValid(sourceField, field, movedFigure);
                validFields.addFieldValidation(field, validation);
            }
        }

        return validFields;
    }


    public Validation isValid(Field sourceField, Field targetField, Figure movedFigure) {

        Board board = gameService.getCurrentBoard();
        Validation validation = new Validation(null);

        switch (movedFigure.getFigureType()) {
            case PAWN -> validation = new Validation(new ValidatePawn(board, sourceField, targetField));
            case ROOK -> validation = new Validation(new ValidateRook(board, sourceField, targetField));
            case BISHOP -> validation = new Validation(new ValidateBishop(board, sourceField, targetField));
            case QUEEN -> validation = new Validation(new ValidateQueen(board, sourceField, targetField));
            case KNIGHT -> validation = new Validation(new ValidateKnight(board, sourceField, targetField));
            case KING -> validation = new Validation(new ValidateKing(board, sourceField, targetField));
        }

        validation.executeValidation();

        return validation;
    }

}
