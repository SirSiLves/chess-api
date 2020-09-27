package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private PawnPromotionService pawnPromotionService;

    private static final int MINMAX_LEVEL = 3;
    private static final Map<FigureType, Integer> FIGURE_WORTH = new HashMap<>() {
        {
            put(FigureType.PAWN, 10);
            put(FigureType.KNIGHT, 30);
            put(FigureType.BISHOP, 30);
            put(FigureType.ROOK, 50);
            put(FigureType.QUEEN, 90);
            put(FigureType.KING, 900);
        }
    };


    public void executeRandomBotMove() {
        this.executeMinMaxBot();

//        this.executeRandomBot();
    }

    private void executeRandomBot(){
        List<Figure> botFigures = figureService.getAtTurnFigures();

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
            pawnPromotionService.changePawn(selectedFigure);
        }
    }

    private void executeMinMaxBot() {

        int highestRate = 0;
        Field highestRatedTargetField = null;
        Figure highestRatedFigure = null;
        List<Figure> botFigures = figureService.getAtTurnFigures();


        for(Figure figure : botFigures){

            Field sourceField = figureService.getFigureField(figure);
            ValidFields validFields = validFieldService.validateFields(sourceField, figure);

            Field bestLevelField = null;
            int worthLevel = 0;

            for(Field targetField : validFields.getFieldList().keySet()){

                Validation validation = validateService.validateMove(sourceField, targetField);
                if(validation.isState()){

                    //retrieve the best move
                    int tempLevel = this.getWorthLevel(targetField);
                    if(tempLevel >= worthLevel) {
                        worthLevel = tempLevel;
                        bestLevelField = targetField;
                    }
                }
            }

            if(worthLevel >= highestRate && figure != null && bestLevelField != null){
                highestRate = worthLevel;
                highestRatedTargetField = bestLevelField;
                highestRatedFigure = figure;
            }

        }

        this.executeBestRated(highestRatedFigure, highestRatedTargetField);

    }

    private int getWorthLevel(Field targetField) {
        Figure targetFigure = targetField.getFigure();

        if(targetFigure != null){
            return FIGURE_WORTH.get(targetFigure.getFigureType());
        }
        else {
            return 0;
        }
    }

    private void executeBestRated(Figure figure, Field targetField){
        Field sourceField = figureService.getFigureField(figure);
        moveExecutorService.executeMove(sourceField, targetField, false);

        //Check if pawn reaches one of the border and replace it
        this.handlePawn(targetField);
    }

}
