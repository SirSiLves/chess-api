package me.rsls.chessapi.model.validation.figures;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.validation.IValidate;
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

    private void checkUpToDown(int tempSourceNumber, int tempTargetNumber,
                               int sourceIndex, int targetIndex) {

        int index = sourceIndex;

        for (int i = tempSourceNumber; i < tempTargetNumber; i++) {

            if (this.checkNextField(index, sourceIndex, targetIndex, i, 1)) {
                //dont check more fields
                break;
            } else {
                if (index < targetIndex) index++;
                else index--;
            }
        }
    }

    private void checkDownToUp(int tempSourceNumber, int tempTargetNumber,
                               int sourceIndex, int targetIndex) {

        int index = sourceIndex;

        for (int i = tempSourceNumber; i > tempTargetNumber; i--) {

            if (this.checkNextField(index, sourceIndex, targetIndex, i, -1)) {
                //dont check more fields
                break;
            } else {
                if (index > targetIndex) index--;
                else index++;
            }
        }
    }

    private boolean checkNextField(int index, int sourceIndex,
                                   int targetIndex, int i, int direction) {

        int startIndex, endIndex;

        if (sourceIndex < targetIndex) {
            startIndex = 1;
            endIndex = 2;
        } else {
            startIndex = -1;
            endIndex = 0;
        }

        if (index + startIndex >= 0 && index + startIndex < 8 ) {
            String verticalValue = BoardService.VERTICAL_DESIGNATION.substring(index + startIndex, index + endIndex);
            Field nextField = this.board.getFieldFromMatrix(verticalValue, i + direction);

            if (targetField.getFieldDesignation() == nextField.getFieldDesignation() && this.checkDestroy()) {
                this.isValid = true;
                return true;
            } else {
                return nextField.getFigure() != null;
            }
        }
        else {
            return false;
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
