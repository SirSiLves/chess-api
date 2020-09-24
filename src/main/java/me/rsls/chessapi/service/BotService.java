package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BotService {

    @Autowired
    private GameService gameService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private PawnChangerService pawnChangerService;


    public void executeRandomBotMove() {

        Board board = gameService.getCurrentBoard();

        List<Figure> botFigures = board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> !board.getLastPlayed().equals(f.getFigureColor()))
                .collect(Collectors.toList());

        boolean moveExecuted = false;
        while (!moveExecuted) {
            Figure randomFigure = botFigures.get(new Random().nextInt(botFigures.size()));

            Field sourceField = figureService.getFigureField(randomFigure);
            ValidFields validFields = validFieldService.validateFields(sourceField, sourceField.getFigure());

            if (this.validateBotMove(sourceField, validFields)) {
                moveExecuted = true;
            }
        }
    }

    private boolean validateBotMove(Field sourceField, ValidFields validFields) {

        if (validFields.getFieldList().size() > 0) {
            for (Field targetField : validFields.getFieldList().keySet()) {

                Validation validation = validateService.validateMove(sourceField, targetField);

                if (validation.isState()) {
                    moveExecutorService.executeMove(sourceField, targetField, false);

                    //Check if pawn reaches one of the border and replace it
                    this.handlePawn(targetField);

                    return true;
                }
            }
        }
        return false;
    }

    private void handlePawn(Field targetField) {
        if (targetField.getFigure().getFigureType().equals(FigureType.PAWN) &&
                (targetField.getHorizontalNumber() == 1 || targetField.getHorizontalNumber() == 8)) {

            SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
            pawnChangerService.changePawn(selectedFigure);
        }
    }
}
