package me.rsls.chessapi.service.validation;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.BoardService;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.MoveExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastlingService {

    @Autowired
    private CheckService checkService;

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ValidFieldService validFieldService;


    public boolean validateCastling(Field sourceField, Field targetField) {

        int sourceX = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
        int targetX = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());
        int sourceY = sourceField.getHorizontalNumber();
        int targetY = targetField.getHorizontalNumber();

        //if its a castling move but not valid return false, else return true
        if (this.isCastlingMove(sourceX, targetX, sourceY, targetY, targetField)) {

            //set castling state to know which move should be executed later
            GameState gameState = gameService.getCurrentGameState();
            gameState.setCastling(true);

            //castling field must in the valid field list
            ValidFields kingValidFields = validFieldService.validateFields(sourceField, sourceField.getFigure());
            Validation validation = kingValidFields.getFieldList().get(targetField);

            if (validation == null) {
                return false;
            } else if (targetX - sourceX == -2) {
                return validateQueenSideCastling(sourceField, targetField, kingValidFields);
            } else {
                return validateKingSideCastling(sourceField, targetField, kingValidFields);
            }

        } else {
            return true;
        }
    }

    private boolean isCastlingMove(int sourceX, int targetX, int sourceY, int targetY, Field targetField) {
        return (Math.abs(sourceX - targetX) == 2)
                && (Math.abs(sourceY - targetY) == 0)
                && targetField.getFigure() == null;
    }

    private boolean validateKingSideCastling(Field sourceField, Field targetField, ValidFields validFields) {

        //exchange on top
        if (sourceField.getHorizontalNumber() == 1) {
        }

        //exchange at bottom
        else if (sourceField.getHorizontalNumber() == 8) {

            Field f8 = boardService.getField(new String[]{"f", "8"});

            GameState gameState = new GameState();
            checkService.validateCheck(sourceField, f8, gameState);

            if (gameState.isCheck()) {
                return false;
            }

            Field g8 = boardService.getField(new String[]{"g", "8"});
            checkService.validateCheck(sourceField, g8, gameState);

            if (gameState.isCheck()) {
                return false;
            }

        }

        return true;
    }

    private boolean validateQueenSideCastling(Field sourceField, Field targetField, ValidFields validFields) {
        //validate on top
        if (sourceField.getHorizontalNumber() == 1) {

        }
        //validate at bottom
        else if (sourceField.getHorizontalNumber() == 8) {

        }


        return true;
    }


}
