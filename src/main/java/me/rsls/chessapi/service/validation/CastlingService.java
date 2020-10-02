package me.rsls.chessapi.service.validation;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.BoardService;
import me.rsls.chessapi.service.GameService;
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
                return validateQueenSideCastling(sourceField);
            } else {
                return validateKingSideCastling(sourceField);
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

    private boolean validateCheckWay(String firstColumn, String secondColumn, String rowNumber, Field sourceField) {
        Field f = boardService.getField(new String[]{firstColumn, rowNumber});
        if (this.validateCheck(sourceField, f)) {
            return false;
        }

        Field s = boardService.getField(new String[]{secondColumn, rowNumber});
        return !this.validateCheck(sourceField, s);
    }

    private boolean validateCheck(Field sourceField, Field wayField) {
        GameState gameState = new GameState();
        checkService.validateCheck(sourceField, wayField, gameState);

        return gameState.isCheck();
    }

    private boolean validateKingSideCastling(Field sourceField) {
        //exchange on top
        if (sourceField.getHorizontalNumber() == 1) {
            if(isMoved("1", "h")) return false;
            return this.validateCheckWay("f", "g", "1", sourceField);
        }
        //exchange at bottom
        else if (sourceField.getHorizontalNumber() == 8) {
            if(isMoved("8", "h")) return false;
            return this.validateCheckWay("f", "g", "8", sourceField);
        }

        return false;
    }

    private boolean validateQueenSideCastling(Field sourceField) {
        //exchange on top
        if (sourceField.getHorizontalNumber() == 1) {
            if(isMoved("1", "a")) return false;
            return this.validateCheckWay("d", "c", "1", sourceField);
        }
        //exchange at bottom
        else if (sourceField.getHorizontalNumber() == 8) {
            if(isMoved("8", "a")) return false;
            return this.validateCheckWay("d", "c", "8", sourceField);
        }

        return false;
    }

    private boolean isMoved(String rowNumber, String rookColumn) {
        Figure king = boardService.getField(new String[]{"e", rowNumber}).getFigure();
        Figure rook = boardService.getField(new String[]{rookColumn, rowNumber}).getFigure();

        Board board = gameService.getCurrentBoard();

        for (History history : board.getMoveHistory().values()) {
            Figure movedFigure = history.getMovedFigure();

            if (movedFigure.equals(king) || movedFigure.equals(rook)) {
                return true;
            }
        }

        return false;
    }


}
