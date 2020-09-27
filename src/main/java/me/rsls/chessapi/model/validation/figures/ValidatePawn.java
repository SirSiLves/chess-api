package me.rsls.chessapi.model.validation.figures;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Color;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.validation.IValidate;
import me.rsls.chessapi.service.BoardService;


public class ValidatePawn implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;


    public ValidatePawn(Board board, Field sourceField, Field targetField) {
        this.isValid = false;
        this.board = board;
        this.sourceField = sourceField;
        this.targetField = targetField;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }

    @Override
    public void verifyMove() {
        int sourceNumber = sourceField.getHorizontalNumber();
        int targetNumber = targetField.getHorizontalNumber();

        if (board.getLastPlayed() == null
                || (sourceField.getFigure().getFigureColor().equals(Color.BLACK) && targetNumber - sourceNumber > 0)
                || (sourceField.getFigure().getFigureColor().equals(Color.WHITE) && sourceNumber - targetNumber > 0)) {

            if (targetField.getFigure() != null
                    && Math.abs(targetNumber - sourceNumber) == 1) {
                this.checkDestroy();
            } else {
                this.checkStraightMove();
            }
        }
    }

    public void checkDestroy() {
        int sourceIndex = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical()); //7
        int targetIndex = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical()); //6 oder 8
        int difference = sourceIndex - targetIndex;

        if (Math.abs(difference) == 1 && !sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor())) {
            this.isValid = true;
        }
    }

    public void checkStraightMove() {
        int sourceNumber = sourceField.getHorizontalNumber();
        int targetNumber = targetField.getHorizontalNumber();

        if (sourceField.getVertical().equals(targetField.getVertical())) {
            if (Math.abs(sourceNumber - targetNumber) == 1) {
                this.isValid = true;

            } else if (Math.abs(sourceNumber - targetNumber) == 2
                    && (sourceField.getHorizontalNumber() == 7 || sourceField.getHorizontalNumber() == 2)
                    && targetField.getFigure() == null) {

                // check if a figure is between
                if (sourceField.getFigure().getFigureColor().equals(Color.WHITE)
                        && board.getFieldFromMatrix(sourceField.getVertical(), sourceNumber - 1).getFigure() == null) {

                    this.isValid = true;
                } else if (sourceField.getFigure().getFigureColor().equals(Color.BLACK)
                        && board.getFieldFromMatrix(sourceField.getVertical(), targetNumber - 1).getFigure() == null) {

                    this.isValid = true;
                }
            }
        }
    }
}
