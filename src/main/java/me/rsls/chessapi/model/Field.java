package me.rsls.chessapi.model;

public class Field {

    private final Color fieldColor;
    private final String[] fieldDesignation;
    private Figure figure;

    public Field(Color fieldColor, String[] fieldDesignation) {
        this.fieldColor = fieldColor;
        this.fieldDesignation = fieldDesignation;
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public String[] getFieldDesignation() {
        return fieldDesignation;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

}


