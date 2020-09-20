package me.rsls.chessapi.service;


import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidateService {

    @Autowired
    private CheckService checkService;

    @Autowired
    private CheckMateService checkMateService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private RemisService remisService;

    @Autowired
    private GameService gameService;

    private static final Map<Integer, String> RULE_TEXTS = new HashMap<>() {
        {
            put(1, "Valid");
            put(2, "No figure on current field");
            put(3, "The other player's turn");
            put(4, "Invalid move");
            put(5, "Your figure is in a check state");
            put(6, "Check!");
            put(7, "Checkmate!");
            put(8, "Remis");
            put(9, "Your king runs into Check!");
        }
    };

    public Validation validateMove(Field sourceField, Field targetField) {
        Validation validation = new Validation(null);

        Board board = gameService.getCurrentBoard();

        if (sourceField.getFigure() == null) {
            validation.setText(RULE_TEXTS.get(2));
        } else if (sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())) {
            validation.setText(RULE_TEXTS.get(3));
        } else if (sourceField.getFigure() != null) {

            Figure movedFigure = sourceField.getFigure();

            //collect all possible fields for moved figure
            ValidFields validFields = validFieldService.validateFields(sourceField, movedFigure);

            validation = validFields.getFieldList().get(targetField);

            if (validation != null && validation.isState()) {
                if (board.getMoveHistory().size() > 0) {

                    boolean checkStateBefore = false;
                    GameState gameState = gameService.getCurrentGameState();

                    if (gameState.isCheck()) checkStateBefore = true;

                    checkService.validateCheck(sourceField, targetField, gameState);

                    //king can not jump into a check state
                    if (sourceField.getFigure().getFigureType().equals(FigureType.KING)
                            && gameState.isCheck()) {
                        gameState.setCheck(false);
                        validation = new Validation(null);
                        validation.setText(RULE_TEXTS.get(9));

                    } else {
                        if (checkStateBefore && gameState.isCheck() ||
                                (gameState.getCheckColor() != null && !gameState.getCheckColor().equals(board.getLastPlayed()))) {

                            //invalid move, its still check
                            validation = new Validation(null);
                            validation.setText(RULE_TEXTS.get(6));

                        } else {

                            if (gameState.isCheck()) {
                                checkMateService.validateCheckMate(sourceField, targetField);

                                if (gameState.isCheckMate()) validation.setText(RULE_TEXTS.get(7));
                                else validation.setText(RULE_TEXTS.get(6));

                            } else {
                                remisService.validateRemis(sourceField, targetField);

                                if (gameState.isRemis()) {
                                    validation.setText(RULE_TEXTS.get(9));
                                } else {
                                    validation.setText(RULE_TEXTS.get(1));
                                }
                            }
                        }
                    }
                }
                else {
                    validation.setText(RULE_TEXTS.get(1));
                }

            }
            else {
                validation = new Validation(null);
                validation.setText(RULE_TEXTS.get(4));
            }
        }

        return validation;
    }


}
