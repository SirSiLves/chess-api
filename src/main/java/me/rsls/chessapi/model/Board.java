package me.rsls.chessapi.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private final HashMap<String, HashMap<Integer, Field>> fieldMatrix;
    private final ArrayList<Figure> figureArrayList;
    private Color lastPlayed;
    private final HashMap<Integer, Move> moveHistory; // 1,2,3,4.. <Move Count, Move Object>

    public Board() {
        this.fieldMatrix = new HashMap<>();
        this.figureArrayList = new ArrayList<>();
        this.lastPlayed = null;
        this.moveHistory = new HashMap<>();
    }

    public HashMap<String, HashMap<Integer, Field>> getFieldMatrix() {
        return fieldMatrix;
    }

    public void addFigureArrayList(Figure figure){
        figureArrayList.add(figure);
    }

    public ArrayList<Figure> getFigureArrayList() {
        return figureArrayList;
    }

    public Color getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Color playerTurn) {
        this.lastPlayed = playerTurn;
    }

    public void addMoveToHistory(Move move){
        moveHistory.put(moveHistory.size(), move);

    }

    public HashMap<Integer, Move> getMoveHistory() {
        return moveHistory;
    }

    public Field getFieldFromMatrix(String verticalValue, int horizontalValue){
        return this.getFieldMatrix().get(verticalValue).get(horizontalValue);
    }
}
