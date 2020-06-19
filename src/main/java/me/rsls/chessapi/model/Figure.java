package me.rsls.chessapi.model;

public class Figure {

    private final FigureType figureType;
    private boolean isAlive;

    public Figure(FigureType figureType){
        this.figureType = figureType;
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
}

enum FigureType{
    King, Queen, Rook, Bishop, Knight, Pawn
}
