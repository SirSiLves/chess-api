package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class CheckService {

    @Autowired
    private FigureService figureService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;


    public void validateCheck(Field sourceField, Field targetField, GameState gameState) {

        boolean checkStateBefore = gameState.isCheck();

        //reset check state
        this.resetCheckState(gameState);

        GameState gameStateBlack = new GameState();
        processCheckValidation(sourceField, targetField, Color.BLACK, gameStateBlack);

        GameState gameStateWhite = new GameState();
        processCheckValidation(sourceField, targetField, Color.WHITE, gameStateWhite);

        //not allowed to have both kings in a check state
        if(gameStateBlack.isCheck() && gameStateWhite.isCheck()){
            gameState.setDoubleCheck(true);
            gameState.setCheck(checkStateBefore);
        }
        else {
            if (gameStateBlack.getCheckColor() != null) {
                gameState.setCheck(true);
                gameState.setCheckColor(gameStateBlack.getCheckColor());
            } else if (gameStateWhite.getCheckColor() != null) {
                gameState.setCheck(true);
                gameState.setCheckColor(gameStateWhite.getCheckColor());
            }
        }
    }

    private void resetCheckState(GameState gameState) {
        gameState.setDoubleCheck(false);
        gameState.setCheck(false);
        gameState.setCheckColor(null);
    }

    private void processCheckValidation(Field sourceField, Field targetField, Color kingColor, GameState gameState) {

        boolean isCheck = false;
        Color checkColor = null;

        if (targetField.getFigure() != null && targetField.getFigure().getFigureType().equals(FigureType.KING)) {
            gameState.setCheck(true);
            gameState.setCheckColor(kingColor);
        } else {
            //execute move, to check the new situation
            moveExecutorService.executeMove(sourceField, targetField, true);

            //get only enemies of the king
            List<Figure> enemyList = figureService.getEnemies(kingColor);

            Figure king = figureService.getKing(kingColor);

            Field kingField = figureService.getFigureField(king);

            for(Figure figure : enemyList) {
                Field figureField = figureService.getFigureField(figure);
                ValidFields figureValidTargetFields = validFieldService.validateFields(figureField, figure);

                if (figureValidTargetFields.getFieldList().get(kingField) != null) {
                    isCheck = true;
                    checkColor = kingColor;

                    break;
                }
            }

            moveExecutorService.revertLastMove(true);

        }

        gameState.setCheck(isCheck);
        gameState.setCheckColor(checkColor);
    }

}
