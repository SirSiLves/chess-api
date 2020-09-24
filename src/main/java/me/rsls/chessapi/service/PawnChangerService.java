package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PawnChangerService {

    @Autowired
    private GameService gameService;

    @Autowired
    private FigureService figureService;


    public void changePawn(SelectedFigure selectedFigure) {
        FigureType selectedFigureType = selectedFigure.getFigureType();

        Field borderPawnField = this.getBorderPawnField();

        Figure newFigure = new Figure(selectedFigureType, borderPawnField.getFigure().getFigureColor());
        borderPawnField.getFigure().setAlive(false);
        borderPawnField.setFigure(newFigure);

        gameService.getCurrentGameState().setPawnChange(false);
        gameService.getCurrentBoard().getFigureArrayList().add(newFigure);
    }

    public Field getBorderPawnField() {
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


    public void handlePawnChangeState(Field targetField) {
        if (targetField.getFigure().getFigureType().equals(FigureType.PAWN)
                && (targetField.getHorizontalNumber() == 8 || targetField.getHorizontalNumber() == 1)) {

            gameService.getCurrentGameState().setPawnChange(true);
        }
    }

}
