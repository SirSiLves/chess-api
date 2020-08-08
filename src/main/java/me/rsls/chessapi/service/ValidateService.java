package me.rsls.chessapi.service;


import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.Validation;
import me.rsls.chessapi.model.validation.ValidatePawn;
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

        Validation validation = new Validation();

        if(sourceField.getFigure() == null) validation.setText(RULE_TEXTS.get(2));

        else if(sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())){
            validation.setText(RULE_TEXTS.get(3));
        }

        else if (sourceField.getFigure().getFigureType() == FigureType.PAWN){
             ValidatePawn vPawn = new ValidatePawn(board, sourceField, targetField);
             vPawn.verifyMove();

             if(vPawn.isValid()){
                 validation.setState(true);
                 validation.setText(RULE_TEXTS.get(1));
             }
             else validation.setText(RULE_TEXTS.get(4));
        }




        return validation;
    }


}
