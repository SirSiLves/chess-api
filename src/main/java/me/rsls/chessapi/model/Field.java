package me.rsls.chessapi.model;

public class Field {

    private final Color fieldColor;
    private final char[][] fieldDesignation;
    private Figure figure;

    public Field(Color fieldColor, char[][] fieldDesignation) {
        this.fieldColor = fieldColor;
        this.fieldDesignation = fieldDesignation;
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public char[][] getFieldDesignation() {
        return fieldDesignation;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}


