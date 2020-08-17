package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Color;
import me.rsls.chessapi.model.Field;
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
        String sourceVertical = sourceField.getVertical();
        String targetVertical = targetField.getVertical();


        if((sourceNumber == targetNumber && !sourceVertical.equals(targetVertical))
                || (sourceNumber != targetNumber && sourceVertical.equals(targetVertical))){

            if(targetField.getFigure() != null){
                this.checkDestroy();
            }
            else{
                this.checkStraightMove();
            }

//            this.setValid(true);
        }

    }


    public void checkDestroy(){

        if(!sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor())){
            this.setValid(true);
        }

    }

    public void checkStraightMove(){

    }

    public void checkFigureBetween(){

        int sourceIndex = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical()); //7
        int targetIndex = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical()); //6 oder 8






    }


}
