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

    @Autowired
    private BoardService boardService;


    public void executeMove(Field sourceField, Field targetField, boolean includeAutoPromotion) {
        GameState gameState = gameService.getCurrentGameState();

        if (gameState.isCastling()) {
            this.executeCastling(sourceField);
        } else {
            this.executeNormalMove(sourceField, targetField);
        }

        if (includeAutoPromotion) this.executePromotion();
    }

    public void executeNormalMove(Field sourceField, Field targetField) {
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
        History history = new History(sourceField, targetField, movedFigure, killedFigure, MoveType.NORMAL, historyGameState);
        board.addMoveToHistory(history);

        //set last played
        board.setLastPlayed(movedFigure.getFigureColor());

        //handle pawn state, if one reaches the border
        pawnPromotionService.validatePromotion(targetField);
    }

    public void revertLastMove(boolean includeAutoPromotion) {
        Board board = gameService.getCurrentBoard();

        //remove last pawn change
        if (includeAutoPromotion) this.revertPromotion(board);

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
        GameState historyGameState = history.getGameState();

        currentGameState.setCheck(historyGameState.isCheck());
        currentGameState.setDoubleCheck(historyGameState.isDoubleCheck());
        currentGameState.setCheckColor(historyGameState.getCheckColor());
        currentGameState.setCheckMate(historyGameState.isCheckMate());
        currentGameState.setWinner(historyGameState.getWinner());
        currentGameState.setRemis(historyGameState.isRemis());
        currentGameState.setRemisReason(historyGameState.getRemisReason());
        currentGameState.setPromotion(history.isMoveType().equals(MoveType.PROMOTION));
        currentGameState.setCastling(history.isMoveType().equals(MoveType.CASTLING));
    }

    private void executePromotion() {
        //check if promotion has to be done, if yes do it, if include promotion is true
        GameState gameState = gameService.getCurrentGameState();

        if (gameState.isPromoted()) {
            SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
            pawnPromotionService.promotePawn(selectedFigure);
        }
    }

    private void revertPromotion(Board board) {
        History history = board.getMoveHistory().get(board.getMoveHistory().size() - 1);

        if (history.isMoveType().equals(MoveType.PROMOTION)) {
            GameState gameState = gameService.getCurrentGameState();
            gameState.setPromotion(false);

            Field lastSourceField = history.getSourceField();
            Figure newFigure = history.getMovedFigure();
            Figure pawnFigure = history.getKilledFigure();

            //revive killed figure
            if (pawnFigure != null) pawnFigure.setAlive(true);
            newFigure.setAlive(false);

            //move back to start
            lastSourceField.setFigure(pawnFigure);

            //delete last entry in history
            board.getMoveHistory().entrySet().removeIf(m -> m.getValue().equals(history));
        }

    }

    private void executeCastling(Field sourceField) {

        //exchange on top
        if (sourceField.getHorizontalNumber() == 1) {
        }

        //exchange at bottom
        else if (sourceField.getHorizontalNumber() == 8) {

            Field oldKingField = boardService.getField(new String[]{"e", "8"});
            Field oldRookField = boardService.getField(new String[]{"h", "8"});

//            Figure king = oldKingField.getFigure();
//            Figure rook = oldRookField.getFigure();

            Field newKingField = boardService.getField(new String[]{"g", "8"});
            Field newRookField = boardService.getField(new String[]{"f", "8"});

//            oldKingField.setFigure(null);
//            oldRookField.setFigure(null);
//
//            newKingField.setFigure(king);
//            newRookField.setFigure(rook);

            Board board = gameService.getCurrentBoard();


        }
    }

    private void revertCastling(History history) {

    }


}
