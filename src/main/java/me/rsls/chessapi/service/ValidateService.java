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
    private ValidFieldService validFieldService;

    private static final Map<Integer, String> RULE_TEXTS = new HashMap<>() {
        {
            put(1, "Valid");
            put(2, "No figure on current field");
            put(3, "The other player's turn");
            put(4, "Invalid move");
            put(5, "Your figure is in a check state");
            put(6, "Checkmate!");
            put(7, "Check!");
            put(8, "Remis");
        }
    };

    public Validation validateMove(Board board, Field sourceField, Field targetField) {
        Validation validation = new Validation(null);

        if (sourceField.getFigure() == null) {
            validation.setText(RULE_TEXTS.get(2));
        } else if (sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())) {
            validation.setText(RULE_TEXTS.get(3));
        } else if (sourceField.getFigure() != null) {

            Figure movedFigure = sourceField.getFigure();
            //collect all possible fields for moved figure
            ValidFields validFields = validFieldService.validateFields(board, sourceField, movedFigure);

            validation = validFields.getFieldList().get(targetField);

            if (validation.isState()) {

                boolean checkStateBefore = false;
                CheckState checkState = board.getCheck();
                if (checkState.isCheck()) {
                    checkStateBefore = true;
                }

                checkState = checkService.validateCheck(board, sourceField, targetField);

                if (checkStateBefore && checkState.isCheck()
                        || (checkState.getCheckColor() != null && !checkState.getCheckColor().equals(board.getLastPlayed()))) {

                    validation = new Validation(null);
                    validation.setText(RULE_TEXTS.get(7));

                } else {
                    if (checkState.isCheckMate()) {
                        validation.setText(RULE_TEXTS.get(6));
                    } else if (checkState.isRemis()) {
                        validation.setText(RULE_TEXTS.get(8));
                    } else {
                        validation.setText(RULE_TEXTS.get(1));
                    }
                }
            } else {
                validation.setText(RULE_TEXTS.get(4));
            }
        }

        return validation;
    }


}
