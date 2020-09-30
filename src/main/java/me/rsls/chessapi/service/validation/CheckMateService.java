package me.rsls.chessapi.service.validation;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.service.FigureService;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.MoveExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckMateService {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private ValidFieldService validFieldService;


    public void validateCheckMate(Field sourceField, Field targetField) {
        GameState currentGameState = gameService.getCurrentGameState();

        boolean canProtect = false;
        boolean isCheckMate = false;

        Figure king = figureService.getKing(currentGameState.getCheckColor());
//        Field kingField = figureService.getFigureField(king);

        //pre execute move
        moveExecutorService.executeMove(sourceField, targetField, true);

//        ValidFields kingValidFields = validFieldService.validateFields(kingField, king);

        //get protectors of the king
        List<Figure> guardiens = figureService.getAllies(king.getFigureColor());

        canProtect = isCanProtect(guardiens);

        if (!canProtect) {
            isCheckMate = true;
        }

        moveExecutorService.revertLastMove(true);

        if (currentGameState.getCheckColor().equals(Color.BLACK)) {
            gameService.getCurrentGameState().setWinner(Color.WHITE);
        } else {
            gameService.getCurrentGameState().setWinner(Color.BLACK);
        }

        currentGameState.setCheckMate(isCheckMate);
    }

    public boolean isCanProtect(List<Figure> guardiens) {
        for (Figure g : guardiens) {
            Field guardSourceField = figureService.getFigureField(g);
            ValidFields validGuardFields = validFieldService.validateFields(guardSourceField, g);

            //check if a mate can prevent the check state
            for (Field guardTargetField : validGuardFields.getFieldList().keySet()) {
                GameState gameState = new GameState();

                checkService.validateCheck(guardSourceField, guardTargetField, gameState);
                // a mate can prevent the check
                if (!gameState.isCheck() && !gameState.isDoubleCheck()) {
                    return true;
                }
            }
        }
        return false;
    }
}
