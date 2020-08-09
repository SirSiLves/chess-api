package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;

public class ValidateRook implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;


    public ValidateRook(Board board, Field sourceField, Field targetField) {
        this.isValid = false;
        this.board = board;
        this.sourceField = sourceField;
        this.targetField = targetField;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public void verifyMove() {
        int sourceNumber = sourceField.getHorizontalNumber();
        int targetNumber = targetField.getHorizontalNumber();

        this.setValid(true);
    }


}
