package me.rsls.chessapi.model;

public class Field {

    private final Color fieldColor;
    private final String[] fieldDesignation;
    private Figure figure;

    public Field(Color fieldColor, String[] fieldDesignation) {
        this.fieldColor = fieldColor;
        this.fieldDesignation = fieldDesignation;
        this.figure = null;
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public String[] getFieldDesignation() {
        return fieldDesignation;
    }

    public String getVertical(){
        return fieldDesignation[0];
    }

    public int getHorizontalNumber(){
        return Integer.parseInt(this.fieldDesignation[1]);
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

}


