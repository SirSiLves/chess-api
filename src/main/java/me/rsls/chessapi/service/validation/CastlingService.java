package me.rsls.chessapi.service.validation;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastlingService {

    @Autowired
    private CheckService checkService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ValidFieldService validFieldService;


    public boolean validateCastling(Field sourceField, Field targetField) {


        if (validateKingSideCastling(sourceField, targetField)) {
            return false;
        }

        if (validateQueenSideCastling(sourceField, targetField)) {
            return false;
        }

        return true;
    }

    private boolean validateQueenSideCastling(Field sourceField, Field targetField) {
        return false;
    }

    private boolean validateKingSideCastling(Field sourceField, Field targetField) {

//        ValidFields figureValidTargetFields = validFieldService.validateFields(sourceField, sourceField.getFigure());
//
//        Validation validation = figureValidTargetFields.getFieldList().get(sourceField);
//        if(validation != null) {
//            return false;
//        }

        return true;
    }



}
