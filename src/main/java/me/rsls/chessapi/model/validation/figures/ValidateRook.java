package me.rsls.chessapi.model.validation.figures;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.validation.IValidate;
import me.rsls.chessapi.service.BoardService;


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
        return this.isValid;
    }

    @Override
    public void verifyMove() {
        int sourceNumber = sourceField.getHorizontalNumber();
        int targetNumber = targetField.getHorizontalNumber();
        String sourceVertical = sourceField.getVertical();
        String targetVertical = targetField.getVertical();

        if (sourceNumber == targetNumber && !sourceVertical.equals(targetVertical)) {

            int sourceIndex = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
            int targetIndex = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());

            if (sourceIndex > targetIndex) {
                this.checkVerticalMove(targetIndex, sourceIndex, targetField);
            } else if (targetIndex > sourceIndex) {
                this.checkVerticalMove(sourceIndex, targetIndex, sourceField);
            }

        } else if (sourceNumber != targetNumber && sourceVertical.equals(targetVertical)) {
            if (sourceNumber > targetNumber) {
                this.checkHorizontalMove(targetNumber, sourceNumber, targetField);
            } else {
                this.checkHorizontalMove(sourceNumber, targetNumber, sourceField);
            }
        }
    }

    private void checkVerticalMove(int sourceIndex, int targetIndex, Field sourceField) {
        for (int i = targetIndex; i > sourceIndex; i--) {
            String verticalValue = BoardService.VERTICAL_DESIGNATION.substring(i - 1, i);
            Field nextField = this.board.getFieldFromMatrix(verticalValue, sourceField.getHorizontalNumber());

            if (nextField.getVertical().equals(sourceField.getVertical())) {
                this.checkDestroy();

            } else if (nextField.getFigure() != null) {
                break; // there is a figure between, wrong move
            }
        }
    }


    private void checkHorizontalMove(int sourceNumber, int targetNumber, Field tempSourceField) {
        for (int i = targetNumber; i > sourceNumber; i--) {

            Field nextField = this.board.getFieldFromMatrix(tempSourceField.getVertical(), i - 1);

            if (nextField.getHorizontalNumber() == tempSourceField.getHorizontalNumber()) {
                this.checkDestroy();

            } else if (nextField.getFigure() != null) {
                break; // there is a figure between, wrong move
            }
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
