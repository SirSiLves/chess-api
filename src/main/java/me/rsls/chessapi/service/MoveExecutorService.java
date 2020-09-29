package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MoveExecutorService {

    @Autowired
    private GameService gameService;

    @Autowired
    private PawnPromotionService pawnPromotionService;


    public void executeMove(Field sourceField, Field targetField, boolean temporarily) {
        Board board = gameService.getCurrentBoard();

        Figure movedFigure = sourceField.getFigure();
        Figure killedFigure = targetField.getFigure();

        //set eliminated figure
        if (killedFigure != null) killedFigure.setAlive(false);

        //set moved figure
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);

        //create history entry
        GameState historyGameState = gameService.getCopyGameState();
        History history = new History(sourceField, targetField, movedFigure, killedFigure, false, historyGameState);
        board.addMoveToHistory(history);

        //set last played
        board.setLastPlayed(movedFigure.getFigureColor());

        //handle pawn state, if one reaches the border
        this.pawnHandling(targetField, temporarily);
    }

    public void revertLastMove(boolean temporarily) {
        Board board = gameService.getCurrentBoard();

        //remove last pawn change
        if(temporarily){
            this.revertPawnSelection(board);
        }

        History lastHistory = board.getMoveHistory().get(board.getMoveHistory().size() - 1);

        Field lastSourceField = lastHistory.getSourceField();
        Field lastTargetField = lastHistory.getTargetField();
        Figure movedFigure = lastHistory.getMovedFigure();
        Figure killedFigure = lastHistory.getKilledFigure();

        //revive killed figure
        if (killedFigure != null) killedFigure.setAlive(true);

        //move back to start
        lastTargetField.setFigure(killedFigure);
        lastSourceField.setFigure(movedFigure);

        //revert game state
        this.revertGameState(lastHistory);

        //delete last entry in history
        board.getMoveHistory().entrySet().removeIf(m -> m.getValue().equals(lastHistory));

        //set last played
        if (movedFigure.getFigureColor().equals(Color.BLACK)) board.setLastPlayed(Color.WHITE);
        else board.setLastPlayed(Color.BLACK);
    }

    private void revertGameState(History history) {
        GameState currentGameState = gameService.getCurrentGameState();
        GameState historyGameState = history.getLastGameState();

        currentGameState.setCheck(historyGameState.isCheck());
        currentGameState.setCheckMate(historyGameState.isCheckMate());
        currentGameState.setRemis(historyGameState.isRemis());
        currentGameState.setCheckColor(historyGameState.getCheckColor());
        currentGameState.setWinner(historyGameState.getWinner());
        currentGameState.setDoubleCheck(historyGameState.isDoubleCheck());
        currentGameState.setPawnChange(historyGameState.isPawnChange());
        currentGameState.setRemisReason(historyGameState.getRemisReason());
    }

    private void pawnHandling(Field targetField, boolean temporarily) {
        GameState gameState = gameService.getCurrentGameState();

        if (pawnPromotionService.getPawnChangeState(targetField)) {
            gameState.setPawnChange(true);
        }

        if(temporarily && gameState.isPawnChange()) {
            SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
            pawnPromotionService.changePawn(selectedFigure);
        }
    }

    private void revertPawnSelection(Board board) {
        History lastHistory = board.getMoveHistory().get(board.getMoveHistory().size() - 1);

        if(lastHistory.isPawnChange()) {
            GameState gameState = gameService.getCurrentGameState();
            gameState.setPawnChange(false);

            Field lastSourceField = lastHistory.getSourceField();
            Figure newFigure = lastHistory.getMovedFigure();
            Figure pawnFigure = lastHistory.getKilledFigure();

            //revive killed figure
            if (pawnFigure != null) {
                pawnFigure.setAlive(true);
            }
            newFigure.setAlive(false);

            //move back to start
            lastSourceField.setFigure(pawnFigure);

            //delete last entry in history
            board.getMoveHistory().entrySet().removeIf(m -> m.getValue().equals(lastHistory));
        }

    }




}
