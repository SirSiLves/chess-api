package me.rsls.chessapi.model.validation.figures;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.IValidate;
import me.rsls.chessapi.service.BoardService;

public class ValidateKing implements IValidate {

    private boolean isValid;
    private final Field sourceField, targetField;
    private final Board board;

    public ValidateKing(Board board, Field sourceField, Field targetField) {
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
        int sourceY = sourceField.getHorizontalNumber();
        int targetY = targetField.getHorizontalNumber();
        int sourceX = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
        int targetX = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());


        if ((Math.abs(sourceX - targetX) == 2)
                && (Math.abs(sourceY - targetY) == 0)
                && targetField.getFigure() == null) {
            if (this.isCastlingValid(sourceX, targetX)) {
                this.isValid = true;
            }
        } else if ((Math.abs(sourceX - targetX) == 1) && (Math.abs(sourceY - targetY) == 0)
                || (Math.abs(sourceX - targetX) == 1) && (Math.abs(sourceY - targetY) == 1)
                || (Math.abs(sourceX - targetX) == 0) && (Math.abs(sourceY - targetY) == 1)) {

            if (this.checkDestroy()) {
                this.isValid = true;
            }
        }
    }

    private boolean isCastlingValid(int sourceX, int targetX) {
        if (this.isMoved()) return false;
        return !this.isFigureBetween(sourceX, targetX);
    }

    private boolean isFigureBetween(int sourceX, int targetX) {

        //check left
        if (targetX - sourceX == -2) {
            Field firstLeftField = this.getTargetField(BoardService.VERTICAL_DESIGNATION.substring(sourceX - 1, sourceX));
            Field secondLeftField = this.getTargetField(BoardService.VERTICAL_DESIGNATION.substring(sourceX - 2, sourceX - 1));
            Field thirdLeftField = this.getTargetField(BoardService.VERTICAL_DESIGNATION.substring(sourceX - 3, sourceX - 2));
            return firstLeftField.getFigure() != null || secondLeftField.getFigure() != null || thirdLeftField.getFigure() != null;
        }
        //check right
        else {
            Field firstRightField = this.getTargetField(BoardService.VERTICAL_DESIGNATION.substring(sourceX + 1, sourceX + 2));
            Field secondRightField = this.getTargetField(BoardService.VERTICAL_DESIGNATION.substring(sourceX + 2, sourceX + 3));
            return firstRightField.getFigure() != null || secondRightField.getFigure() != null;
        }
    }

    private Field getTargetField(String verticalValue) {
        return this.board.getFieldFromMatrix(verticalValue, sourceField.getHorizontalNumber());
    }

    private boolean isMoved() {
        Color figureColor = sourceField.getFigure().getFigureColor();

        boolean isKingMoved = false;
        boolean isRookMoved = false;

        for (History history : board.getMoveHistory().values()) {
            Figure movedFigure = history.getMovedFigure();

            if (movedFigure.getFigureColor().equals(figureColor)) {

                FigureType tempFigureType = movedFigure.getFigureType();

                if (tempFigureType.equals(FigureType.ROOK)) {
                    isRookMoved = true;
                    break;
                } else if (tempFigureType.equals(FigureType.KING)) {
                    isKingMoved = true;
                    break;
                }
            }
        }

        return isKingMoved || isRookMoved;
    }

    private boolean checkDestroy() {

        if (targetField.getFigure() == null) {
            return true;
        } else {
            return !sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor());
        }

    }
}
