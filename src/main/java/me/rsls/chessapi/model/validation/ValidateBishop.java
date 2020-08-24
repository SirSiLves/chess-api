package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
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
            this.checkDiagonal(sourceNumber, targetNumber, sourceIndex, targetIndex);
        }
    }


    private void checkDiagonal(int tempSourceNumber, int tempTargetNumber,
                               int sourceIndex, int targetIndex) {

        if (tempSourceNumber < tempTargetNumber)
            this.checkUpToDown(tempSourceNumber, tempTargetNumber, sourceIndex, targetIndex);
        else {
            this.checkDownToUp(tempSourceNumber, tempTargetNumber, sourceIndex, targetIndex);
        }

    }

    private boolean checkField(int sourceIndex, int targetIndex, int i, int direction) {

        int index = sourceIndex;
        int startIndex, endIndex;

        if (sourceIndex < targetIndex) {
            startIndex = 1;
            endIndex = 2;
        } else {
            startIndex = -1;
            endIndex = 0;
        }

        String verticalValue = BoardService.VERTICAL_DESIGNATION.substring(index + startIndex, index + endIndex);
        Field nextField = this.board.getFieldFromMatrix(verticalValue, i + direction);

        if (nextField.getFieldDesignation() == targetField.getFieldDesignation()) {
            return this.checkDestroy();
        }
        else return false;

    }


    private void checkDownToUp(int tempSourceNumber, int tempTargetNumber,
                               int sourceIndex, int targetIndex) {

        int index = sourceIndex;

        for (int i = tempSourceNumber; i > tempTargetNumber; i--) {

            if(this.checkField(sourceIndex, targetIndex, i, -1)){
                this.isValid = true;
                break;
            }

            if (sourceIndex < targetIndex) index++;
            else index--;
        }

    }


    private void checkUpToDown(int tempSourceNumber, int tempTargetNumber,
                               int sourceIndex, int targetIndex) {

        int index = sourceIndex;

        for (int i = tempSourceNumber; i < tempTargetNumber; i++) {

            if(this.checkField(sourceIndex, targetIndex, i, 1)){
                this.isValid = true;
                break;
            }

            if (sourceIndex < targetIndex) index++;
            else index--;
        }
    }


    private boolean checkDestroy() {

        if (targetField.getFigure() == null) {
            return true;
        } else if (!sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor())) {
            return true;
        } else {
            return false;
        }

    }


}
