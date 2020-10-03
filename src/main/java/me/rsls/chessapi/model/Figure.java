package me.rsls.chessapi.model;

public class Figure {

    private final FigureType figureType;
    private Color figureColor;

    private boolean isAlive;

    public Figure(FigureType figureType, Color figureColor){
        this.figureType = figureType;
        this.figureColor = figureColor;
        this.isAlive = true;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Color getFigureColor() {
        return figureColor;
    }

    public void setFigureColor(Color figureColor) {
        this.figureColor = figureColor;
    }
}

