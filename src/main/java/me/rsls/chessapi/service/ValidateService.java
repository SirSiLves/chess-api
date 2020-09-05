package me.rsls.chessapi.service;


import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.CheckState;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidateService {

    @Autowired
    private CheckService checkService;

    private static final Map<Integer, String> RULE_TEXTS = new HashMap<>() {
        {
            put(1, "Valid");
            put(2, "No figure on current field");
            put(3, "The other player's turn");
            put(4, "Invalid move");
            put(5, "Your figure is in a check state");
            put(6, "Checkmate!");
        }
    };

    public Validation validateMove(Board board, Field sourceField, Field targetField) {
        Validation validation = new Validation(null);

        if (sourceField.getFigure() == null) {
            validation.setText(RULE_TEXTS.get(2));
        } else if (sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())) {
            validation.setText(RULE_TEXTS.get(3));
        } else if (sourceField.getFigure() != null) {

            FigureType figureType = sourceField.getFigure().getFigureType();

            //strategy pattern for move validation
            switch (figureType) {
                case PAWN -> validation = new Validation(new ValidatePawn(board, sourceField, targetField));
                case ROOK -> validation = new Validation(new ValidateRook(board, sourceField, targetField));
                case BISHOP -> validation = new Validation(new ValidateBishop(board, sourceField, targetField));
                case QUEEN -> validation = new Validation(new ValidateQueen(board, sourceField, targetField));
                case KNIGHT -> validation = new Validation(new ValidateKnight(board, sourceField, targetField));
                case KING -> validation = new Validation(new ValidateKing(board, sourceField, targetField));
            }

            validation.executeValidation();


            if (validation.isState()) {

                checkService.validateCheck(board, sourceField, targetField);
                CheckState checkState = board.getCheck();

                if (checkState.isCheck()) {
                    validation = new Validation(null);

                    if (checkState.isCheckMate()) validation.setText(RULE_TEXTS.get(6));
                    else validation.setText(RULE_TEXTS.get(5));

                }
                else validation.setText(RULE_TEXTS.get(1));
            } else validation.setText(RULE_TEXTS.get(4));

        }

        return validation;
    }


}
