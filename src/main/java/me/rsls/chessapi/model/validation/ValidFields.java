package me.rsls.chessapi.model.validation;

import me.rsls.chessapi.model.Field;

import java.util.HashMap;

public class ValidFields {

    private final HashMap<Field, Validation> fieldList;

    public ValidFields(){
        fieldList = new HashMap<>();
    }

    public void addFieldValidation(Field field, Validation validation) {
        this.fieldList.put(field, validation);
    }

    public HashMap<Field, Validation> getFieldList() {
        return fieldList;
    }
}
