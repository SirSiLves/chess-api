package me.rsls.chessapi.service.validation;


import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.*;
import me.rsls.chessapi.service.GameService;
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

    @Autowired
    private CastlingService castlingService;


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
            put(11, "Castling is not allowed");
        }
    };

    public Validation validateMove(Field sourceField, Field targetField) {

        Game currentGame = gameService.getGamePicture();
        Board board = currentGame.getBoard();
        GameState gameState = currentGame.getGameState();
        Validation validation = Validation.createTempValidation();

        //on source field is no figure
        if (sourceField.getFigure() == null) {
            validation.setText(RULE_TEXTS.get(2));
            validation.setState(false);
            return validation;
        }

        //wrong turn
        if (sourceField.getFigure().getFigureColor().equals(board.getLastPlayed())) {
            validation.setText(RULE_TEXTS.get(3));
            validation.setState(false);
            return validation;
        }

        //Castling -> change king with rook
        validation = this.getValidateCastling(sourceField, targetField);
        if(!validation.isState()) {
            return validation;
        }

        //validate if some possible field exists
        validation = this.getValidatePossibleFields(sourceField, targetField);
        if (!validation.isState()) {
            return validation;
        }

        //validate if pawn was changed or not
        validation = this.getValidatePawnChange(gameState);
        if (!validation.isState()) {
            return validation;
        }

        //its not allowed to have both king in check state
        validation = this.getValidateDoubleCheck(sourceField, targetField);
        if (!validation.isState()) {
            return validation;
        }

        //it can not be check behind each other
        validation = this.getValidateStillCheck(gameState, board);
        if (!validation.isState()) {
            return validation;
        }

        //finally handle the check, checkmate or remis state
        this.handleGameStateCheckCheckMateOrRemis(gameState, sourceField, targetField, validation);
        return validation;

    }

    private Validation getValidateCastling(Field sourceField, Field targetField) {
        Validation validation = Validation.createTempValidation();

        //validate rook king exchange - castling
        if (targetField.getFigure() != null
                && sourceField.getFigure().getFigureColor().equals(targetField.getFigure().getFigureColor())
                && sourceField.getFigure().getFigureType().equals(FigureType.KING)) {

            boolean isCastlingAllowed = castlingService.validateCastling(sourceField, targetField);

            if (!isCastlingAllowed) {
                validation.setState(false);
                validation.setText(RULE_TEXTS.get(11));

                return validation;
            }
        }
        return validation;
    }

    private Validation getValidatePawnChange(GameState gameState) {
        Validation validation = Validation.createTempValidation();

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

    private Validation getValidateStillCheck(GameState gameState, Board board) {
        Validation validation = Validation.createTempValidation();

        //overwrite validation object
        if (gameState.getCheckColor() != null && !gameState.getCheckColor().equals(board.getLastPlayed())) {
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(6));
        }
        return validation;
    }

    private Validation getValidateDoubleCheck(Field sourceField, Field targetField) {
        Validation validation = Validation.createTempValidation();

        GameState gameState = gameService.getCurrentGameState();
        checkService.validateCheck(sourceField, targetField, gameState);

        //overwrite validation object
        if (gameState.isDoubleCheck()) {
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
            validation = Validation.createTempValidation();
            validation.setState(false);
            validation.setText(RULE_TEXTS.get(4));
        }
        return validation;
    }


}
