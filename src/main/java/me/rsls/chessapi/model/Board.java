package me.rsls.chessapi.model;

import java.util.ArrayList;

public class Board {

    private Field[][] fieldMatrix;
    private final ArrayList<Figure> figureArrayList;
    private Color winnerColor;

    public Board() {
        this.fieldMatrix = null;
        this.figureArrayList = new ArrayList<>();
        this.winnerColor = null;
    }

    public Field[][] getFieldMatrix() {
        return fieldMatrix;
    }

    public void setFieldMatrix(Field[][] fieldMatrix) {
        this.fieldMatrix = fieldMatrix;
    }

    public ArrayList<Figure> getFigureArrayList() {
        return figureArrayList;
    }

    public Color getWinnerColor() {
        return winnerColor;
    }

    public void setWinnerColor(Color winnerColor) {
        this.winnerColor = winnerColor;
    }
}
