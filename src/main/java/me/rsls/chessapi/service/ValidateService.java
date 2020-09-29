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
            put(9, "Your king is in Check!");
            put(10, "You have to change your pawn");
        }
    };

    public Validation validateMove(Field sourceField, Field targetField) {

        Game currentGame = gameService.getGamePicture();
        Board board = currentGame.getBoard();

        boolean checkStateBefore = false;
        GameState gameState = currentGame.getGameState();

        if (gameState.isCheck()) checkStateBefore = true;

        //on source field is no figure
        if (sourceField.getFigure() == null) {
            Validation validation = new Validation(null);
            validation.setText(RULE_TEXTS.get(2));
            validation.setState(false);
            return validation;
        }

        //friendly fire
        if (sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())) {
            Validation validation = new Validation(null);
            validation.setText(RULE_TEXTS.get(3));
            validation.setState(false);
            return validation;
        }

        //validate if some possible field exists
        Validation validation = this.getValidatePossibleFields(sourceField, targetField);
        if (!validation.isState()) {
            return validation;
        }

        //validate if pawn was changed or not
        validation = this.getValidatePawnChange(sourceField, targetField, validation, gameState);
        if (!validation.isState()) {
            return validation;
        }

        //its not allowed to have both king in check state
        validation = this.getValidateDoubleCheck(sourceField, targetField, validation);
        if (!validation.isState()) {
            return validation;
        }

        //it can not be check behind each other
        validation = this.getValidateStillCheck(gameState, board, checkStateBefore, validation);
        if (!validation.isState()) {
            return validation;
        }

        //finally handle the check, checkmate or remis state
        this.handleGameStateCheckCheckMateOrRemis(gameState, sourceField, targetField, validation);
        return validation;

    }

    private Validation getValidatePawnChange(Field sourceField, Field targetField, Validation validation, GameState gameState) {
        if (gameState.isPawnChange()) {
            validation = new Validation(null);
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(10));
        }

        return validation;
    }

    private void handleGameStateCheckCheckMateOrRemis(GameState gameState, Field sourceField, Field targetField, Validation validation) {
        if (gameState.isCheck()) {
            checkMateService.validateCheckMate(sourceField, targetField);

            if (gameState.isCheckMate()) {
                validation.setText(RULE_TEXTS.get(7));
            } else {
                validation.setText(RULE_TEXTS.get(6));
            }
        } else {
            remisService.validateRemis(sourceField, targetField);

            if (gameState.isRemis()) {
                validation.setText(RULE_TEXTS.get(8));
            } else {
                validation.setText(RULE_TEXTS.get(1));
            }
        }
    }

    private Validation getValidateStillCheck(GameState gameState, Board board, boolean checkStateBefore, Validation validation) {

        //overwrite validation object
        if (gameState.isCheck() && checkStateBefore ||
                (gameState.getCheckColor() != null && !gameState.getCheckColor().equals(board.getLastPlayed()))) {
            validation = new Validation(null);
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(6));
        }
        return validation;
    }

    private Validation getValidateDoubleCheck(Field sourceField, Field targetField, Validation validation) {

        GameState gameState = gameService.getCurrentGameState();
        checkService.validateCheck(sourceField, targetField, gameState);

        //overwrite validation object
        if (gameState.isDoubleCheck()) {
            validation = new Validation(null);
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(9));
        }
        return validation;
    }

    private Validation getValidatePossibleFields(Field sourceField, Field targetField) {
        Figure movedFigure = sourceField.getFigure();

        //collect all possible fields for moved figure
        ValidFields validFields = validFieldService.validateFields(sourceField, movedFigure);
        Validation validation = validFields.getFieldList().get(targetField);

        //overwrite validation object
        if (validation == null) {
            validation = new Validation(null);
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(4));
        }

        return validation;
    }


}
