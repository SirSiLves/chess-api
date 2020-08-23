package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Color;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.service.BoardService;

public class ValidateBishop implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;

    public ValidateBishop(Board board, Field sourceField, Field targetField) {
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

        if (sourceNumber != targetNumber && sourceIndex != targetIndex) {

            if (sourceNumber > targetNumber) {
                this.checkDiagonal(targetNumber, sourceNumber, sourceIndex, targetIndex, targetField, false);
            } else {
                this.checkDiagonal(sourceNumber, targetNumber, sourceIndex, targetIndex, sourceField, true);
            }
        }
    }


    public void checkDiagonal(int sourceNumber, int targetNumber,
                              int sourceIndex, int targetIndex,
                              Field tempSourceField, boolean reverse) {


        int index;
        if (sourceIndex < targetIndex && !reverse) index = sourceNumber;
        else index = targetNumber - 1;


        for (int i = targetNumber; i > sourceNumber; i--) {

            String verticalValue = BoardService.VERTICAL_DESIGNATION.substring(index, index + 1);
            Field nextField = this.board.getFieldFromMatrix(verticalValue, i - 1);

            if (nextField.getHorizontalNumber() == tempSourceField.getHorizontalNumber()) {
                this.checkDestroy();

            } else if (nextField.getFigure() != null) {
                break; // there is a figure between, wrong move
            }

            if (sourceIndex < targetIndex && !reverse) {
                index++;
            } else index--;


        }

    }

    public void checkDestroy() {

        if (targetField.getFigure() == null) {
            this.isValid = true;
        } else if (!sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor())) {
            this.isValid = true;
        }
    }


}
