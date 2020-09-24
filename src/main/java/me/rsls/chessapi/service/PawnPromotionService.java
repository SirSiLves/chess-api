package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PawnPromotionService {

    @Autowired
    private GameService gameService;

    @Autowired
    private FigureService figureService;


    public void changePawn(SelectedFigure selectedFigure) {
        Board board = gameService.getCurrentBoard();
        FigureType selectedFigureType = selectedFigure.getFigureType();

        Field borderPawnField = this.getBorderPawnField();

        Figure oldPawn = borderPawnField.getFigure();
        Figure newFigure = new Figure(selectedFigureType, borderPawnField.getFigure().getFigureColor());

        oldPawn.setAlive(false);
        borderPawnField.setFigure(newFigure);

        gameService.getCurrentGameState().setPawnChange(false);
        gameService.getCurrentBoard().getFigureArrayList().add(newFigure);

        //create history entry
        History history = new History(borderPawnField, borderPawnField, newFigure, oldPawn, true);
        board.addMoveToHistory(history);
    }

    private Field getBorderPawnField() {
        List<Figure> pawnList = gameService.getCurrentBoard().getFigureArrayList()
                .stream()
                .filter(p -> p.getFigureType().equals(FigureType.PAWN))
                .filter(p -> p.isAlive())
                .collect(Collectors.toList());

        for (Figure pawn : pawnList) {
            Field field = figureService.getFigureField(pawn);
            if (field.getHorizontalNumber() == 1 || field.getHorizontalNumber() == 8) {
                return field;
            }
        }

        throw new RuntimeException("It must have a pawn at border. Check frontend!");
    }


    public boolean getPawnChangeState(Field targetField) {
        return targetField.getFigure().getFigureType().equals(FigureType.PAWN)
                && (targetField.getHorizontalNumber() == 8 || targetField.getHorizontalNumber() == 1);
    }




}
