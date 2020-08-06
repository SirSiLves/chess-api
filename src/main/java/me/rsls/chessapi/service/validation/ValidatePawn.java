package me.rsls.chessapi.service.validation;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;

import java.util.ArrayList;
import java.util.Arrays;


public class ValidatePawn {

    private boolean isValid;
    private ArrayList<Field> possibleFieldList;

    public ValidatePawn(){
        this.isValid = false;
        this.possibleFieldList = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public ArrayList<Field> getPossibleField() {
        return possibleFieldList;
    }

    public void addPossibleField(Field possibleField) {
        this.possibleFieldList.add(possibleField);
    }

    public void checkPossibleFields(Board board, Field sourceField, Field targetField) {
        int sourceNumber = sourceField.getHorizontalNumber();
        int targetNumber = sourceField.getHorizontalNumber();

        this.setValid(true);





    }
}
