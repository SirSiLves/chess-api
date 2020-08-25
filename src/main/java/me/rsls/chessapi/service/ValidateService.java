package me.rsls.chessapi.service;


import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.validation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidateService {

    private static final Map<Integer, String> RULE_TEXTS = new HashMap<>() {
        {
            put(1, "Valid");
            put(2, "No figure on current field");
            put(3, "The other player's turn");
            put(4, "Invalid move");
        }
    };

    public Validation validateMove(Board board, Field sourceField, Field targetField){
        Validation validation = new Validation(null);

        if(sourceField.getFigure() == null) {
            validation.setText(RULE_TEXTS.get(2));
        }
        else if(sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())){
            validation.setText(RULE_TEXTS.get(3));
        }

        else if(sourceField.getFigure() != null){

            FigureType figureType = sourceField.getFigure().getFigureType();

            //strategy pattern for move validation
            switch (figureType) {
                case PAWN -> validation = new Validation(new ValidatePawn(board, sourceField, targetField));
                case ROOK -> validation = new Validation(new ValidateRook(board, sourceField, targetField));
                case BISHOP -> validation = new Validation(new ValidateBishop(board, sourceField, targetField));
                case QUEEN -> validation = new Validation(new ValidateQueen(board, sourceField, targetField));

                default -> validation.setState(true);
            }
            validation.executeValidation();


            if(validation.isState()) validation.setText(RULE_TEXTS.get(1));
            else validation.setText(RULE_TEXTS.get(4));

        }

        return validation;
    }


}
