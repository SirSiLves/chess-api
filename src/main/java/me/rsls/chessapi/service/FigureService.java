package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FigureService {

    @Autowired
    private GameService gameService;

    public Field getFigureField(Figure figure) {

        Board board = gameService.getCurrentBoard();

        for(HashMap<Integer, Field> column : board.getFieldMatrix().values()){
            for(Field f : column.values()){
                if (f.getFigure() != null && f.getFigure().equals(figure)) {
                    return f;
                }
            }
        }

        throw new RuntimeException("Each alive figure must exist on the board! Figure: " + figure.getFigureType() + " not found..");
    }


    public Figure getKing(Color kingColor){
        //get king
        Figure king = gameService.getCurrentBoard().getFigureArrayList().stream()
                .filter(f -> f.getFigureType().equals(FigureType.KING))
                .filter(f -> f.getFigureColor().equals(kingColor))
                .findFirst().get();

        return king;
    }

    public List<Figure> getAllies(Color allyColor) {
        Board board = gameService.getCurrentBoard();

        return board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> f.getFigureColor().equals(allyColor))
                .collect(Collectors.toList());
    }

    public List<Figure> getAtTurnFigures() {
        Board board = gameService.getCurrentBoard();
        Color lastPlayed = board.getLastPlayed();

        return board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> !lastPlayed.equals(f.getFigureColor()))
                .collect(Collectors.toList());
    }

    public List<Figure> getEnemies(Color enemyColor) {
        Board board = gameService.getCurrentBoard();

        return board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> !f.getFigureColor().equals(enemyColor))
                .collect(Collectors.toList());
    }

    public void createFigures(Board board) {
        HashMap<String, HashMap<Integer, Field>> tempMatrix = board.getFieldMatrix();

        tempMatrix.get("a").get(1).setFigure(new Figure(FigureType.ROOK, Color.BLACK));
        tempMatrix.get("b").get(1).setFigure(new Figure(FigureType.KNIGHT, Color.BLACK));
        tempMatrix.get("c").get(1).setFigure(new Figure(FigureType.BISHOP, Color.BLACK));
        tempMatrix.get("d").get(1).setFigure(new Figure(FigureType.QUEEN, Color.BLACK));
        tempMatrix.get("e").get(1).setFigure(new Figure(FigureType.KING, Color.BLACK));
        tempMatrix.get("f").get(1).setFigure(new Figure(FigureType.BISHOP, Color.BLACK));
        tempMatrix.get("g").get(1).setFigure(new Figure(FigureType.KNIGHT, Color.BLACK));
        tempMatrix.get("h").get(1).setFigure(new Figure(FigureType.ROOK, Color.BLACK));

        tempMatrix.get("a").get(8).setFigure(new Figure(FigureType.ROOK, Color.WHITE));
        tempMatrix.get("b").get(8).setFigure(new Figure(FigureType.KNIGHT, Color.WHITE));
        tempMatrix.get("c").get(8).setFigure(new Figure(FigureType.BISHOP, Color.WHITE));
        tempMatrix.get("d").get(8).setFigure(new Figure(FigureType.QUEEN, Color.WHITE));
        tempMatrix.get("e").get(8).setFigure(new Figure(FigureType.KING, Color.WHITE));
        tempMatrix.get("f").get(8).setFigure(new Figure(FigureType.BISHOP, Color.WHITE));
        tempMatrix.get("g").get(8).setFigure(new Figure(FigureType.KNIGHT, Color.WHITE));
        tempMatrix.get("h").get(8).setFigure(new Figure(FigureType.ROOK, Color.WHITE));

        this.createPawns(tempMatrix);
        this.addCreatedFiguresToArrayList(tempMatrix, board.getFigureArrayList());
    }

    public void createPawns(HashMap<String, HashMap<Integer, Field>> figuresMatrix) {
        for (HashMap<Integer, Field> r : figuresMatrix.values()) {
            r.get(2).setFigure(new Figure(FigureType.PAWN, Color.BLACK));
            r.get(7).setFigure(new Figure(FigureType.PAWN, Color.WHITE));
        }
    }

    public void addCreatedFiguresToArrayList(HashMap<String, HashMap<Integer, Field>> figuresMatrix, ArrayList<Figure> figureArrayList) {

        for (HashMap<Integer, Field> r : figuresMatrix.values()) {
            for (Field f : r.values()) {
                if (f.getFigure() != null) {
                    figureArrayList.add(f.getFigure());
                }
            }
        }
    }
}
