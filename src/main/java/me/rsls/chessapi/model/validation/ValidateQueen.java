package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;

public class ValidateQueen implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;

    public ValidateQueen(Board board, Field sourceField, Field targetField) {
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

        ValidateBishop validateBishop = new ValidateBishop(board, sourceField, targetField);
        validateBishop.verifyMove();

        if (validateBishop.isValid()) {
            this.isValid = true;

        } else {
            ValidateRook validateRook = new ValidateRook(board, sourceField, targetField);
            validateRook.verifyMove();

            if (validateRook.isValid()) {
                this.isValid = true;
            }
        }

    }


}
