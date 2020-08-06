package me.rsls.chessapi.model;

public class Move {

    private final String[] sourceField, targetField;
    private final Figure figure;

    public Move(Field sourceField, Field targetField, Figure figure) {
        this.sourceField = sourceField.getFieldDesignation();
        this.targetField = targetField.getFieldDesignation();
        this.figure = figure;
    }

    public String[] getSourceField() {
        return sourceField;
    }

    public String[] getTargetField() {
        return targetField;
    }

    public Figure getFigure() {
        return figure;
    }
}
