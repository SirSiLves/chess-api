package me.rsls.chessapi.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private final HashMap<String, HashMap<Integer, Field>> fieldMatrix;
    private final ArrayList<Figure> figureArrayList;

    public Board() {
        this.fieldMatrix = new HashMap<>();
        this.figureArrayList = new ArrayList<>();
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
}
