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
                    CheckState checkState = board.getCheck();

                    if (checkState.isCheck()) checkStateBefore = true;

                    checkService.validateCheck(sourceField, targetField, checkState);

                    if (checkStateBefore && checkState.isCheck() ||
                            (checkState.getCheckColor() != null && !checkState.getCheckColor().equals(board.getLastPlayed()))) {

                        //invalid move, its still check
                        validation = new Validation(null);
                        validation.setText(RULE_TEXTS.get(6));

                    } else {

                        if (checkState.isCheck()) {
                            checkMateService.validateCheckMate(sourceField, targetField);

                            if (checkState.isCheckMate()) validation.setText(RULE_TEXTS.get(7));
                            else if (checkState.isRemis()) validation.setText(RULE_TEXTS.get(8));
                            else validation.setText(RULE_TEXTS.get(6));

                        } else {
                            validation.setText(RULE_TEXTS.get(1));
                        }
                    }
                }

            } else {
                validation = new Validation(null);
                validation.setText(RULE_TEXTS.get(4));
            }
        }

        return validation;
    }


}
