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

    @Autowired
    private MoveExecutorService moveExecutorService;


    public boolean validateCastling(Field sourceField, Field targetField) {

        ValidFields kingValidFields = validFieldService.validateFields(sourceField, sourceField.getFigure());

        //check if target field is one of the king possible target fields
        Validation validation = kingValidFields.getFieldList().get(targetField);
        if (validation == null) {
            return false;
        }

        int sourceX = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
        int targetX = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());

        if (targetX - sourceX == -2) {
            if (!validateQueenSideCastling(sourceField, targetField, kingValidFields)) {
                return false;
            }
        } else {
            if (!validateKingSideCastling(sourceField, targetField, kingValidFields)) {
                return false;
            }
        }

        return true;
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
