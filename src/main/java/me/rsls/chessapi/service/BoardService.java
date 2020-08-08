package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.Color;
import org.springframework.stereotype.Service;


import java.util.HashMap;


@Service
public class BoardService {

    public static final String VERTICAL_DESIGNATION = "abcdefgh";


    public Field getField(Board board, String[] fieldDesignation){
        return board.getFieldMatrix().get(fieldDesignation[0]).get(Integer.parseInt(fieldDesignation[1]));
    }

    public Board createBoard() {
        Board tempBoard = new Board();
        this.createFieldMatrix(tempBoard);

        return tempBoard;
    }

    public void createFieldMatrix(Board board) {
        HashMap<String, HashMap<Integer, Field>> currentMatrix = board.getFieldMatrix();

        currentMatrix.put("a", this.createFieldRow("a", Color.SANDYBROWN));
        currentMatrix.put("b", this.createFieldRow("b", Color.BROWN));
        currentMatrix.put("c", this.createFieldRow("c", Color.SANDYBROWN));
        currentMatrix.put("d", this.createFieldRow("d", Color.BROWN));
        currentMatrix.put("e", this.createFieldRow("e", Color.SANDYBROWN));
        currentMatrix.put("f", this.createFieldRow("f", Color.BROWN));
        currentMatrix.put("g", this.createFieldRow("g", Color.SANDYBROWN));
        currentMatrix.put("h", this.createFieldRow("h", Color.BROWN));

    }

    public HashMap<Integer, Field> createFieldRow(String rowPosition, Color startColor) {
        HashMap<Integer, Field> rowMap = new HashMap<>();

        for (int i = 1; i <= 8; i++) {
            String[] fieldDesignation = new String[2];
            fieldDesignation[0] = rowPosition;
            fieldDesignation[1] = "" + (i);

            if (startColor.equals(Color.SANDYBROWN)) {
                if (i % 2 != 0) {
                    rowMap.put(i, new Field(Color.SANDYBROWN, fieldDesignation));
                } else {
                    rowMap.put(i, new Field(Color.BROWN, fieldDesignation));
                }
            } else {
                if (i % 2 != 0) {
                    rowMap.put(i, new Field(Color.BROWN, fieldDesignation));
                } else {
                    rowMap.put(i, new Field(Color.SANDYBROWN, fieldDesignation));
                }
            }
        }

        return rowMap;
    }
}
