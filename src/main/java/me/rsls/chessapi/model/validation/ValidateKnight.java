package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.service.BoardService;

public class ValidateKnight implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;

    public ValidateKnight(Board board, Field sourceField, Field targetField) {
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
        int sourceIndex = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
        int targetIndex = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());


        if ((Math.abs(sourceIndex - targetIndex) == 1) && (Math.abs(sourceNumber - targetNumber) == 2) ||
                (Math.abs(sourceIndex - targetIndex) == 2) && (Math.abs(sourceNumber - targetNumber) == 1)) {

            if (this.checkDestroy()) {
                this.isValid = true;
            }
        }
    }


    private boolean checkDestroy() {

        if (targetField.getFigure() == null) {
            return true;
        } else {
            return !sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor());
        }

    }
}
