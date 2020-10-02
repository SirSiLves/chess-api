package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.Color;
import me.rsls.chessapi.service.validation.PawnPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MoveExecutorService {

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PawnPromotionService pawnPromotionService;


    public void executeMove(Field sourceField, Field targetField, boolean isTestMove) {
        GameState gameState = gameService.getCurrentGameState();

        if (gameState.isCastling() && !isTestMove) {
            this.executeCastling(sourceField, targetField);
        } else {
            this.executeNormalMove(sourceField, targetField);
        }

        if (isTestMove) this.executePromotion();
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

    public void revertLastMove(boolean isTestMove) {
        Board board = gameService.getCurrentBoard();

        //remove last pawn change
        if (isTestMove) this.revertPromotion(board);

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
        currentGameState.setCastling(historyGameState.isCastling());
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

    private void executeCastling(Field sourceField, Field targetField) {
        int sourceX = BoardService.VERTICAL_DESIGNATION.indexOf(sourceField.getVertical());
        int targetX = BoardService.VERTICAL_DESIGNATION.indexOf(targetField.getVertical());
        int sourceY = sourceField.getHorizontalNumber();
        int targetY = targetField.getHorizontalNumber();

        //exchange on top
        if (sourceY == 1 && targetY == 1) {
            if (targetX - sourceX == -2) {
                //execute left
                this.exchangeCastlingField(sourceField, targetField,
                        "1", "a", "c", "d");
            } else {
                //execute right
                this.exchangeCastlingField(sourceField, targetField,
                        "1", "h", "g", "f");
            }
        }

        //exchange at bottom
        else if (sourceY == 8 && targetY == 8) {
            if (targetX - sourceX == -2) {
                //execute left
                this.exchangeCastlingField(sourceField, targetField,
                        "8", "a", "c", "d");
            } else {
                //execute right
                this.exchangeCastlingField(sourceField, targetField,
                        "8", "h", "g", "f");
            }
        }
    }

    private void exchangeCastlingField(Field sourceField, Field targetField,
                                       String rowNumber, String oldRookColumn,
                                       String newKingColumn, String newRookColumn) {

        Field oldKingField = boardService.getField(new String[]{"e", rowNumber});
        Field oldRookField = boardService.getField(new String[]{oldRookColumn, rowNumber});

        Figure king = oldKingField.getFigure();
        Figure rook = oldRookField.getFigure();

        Field newKingField = boardService.getField(new String[]{newKingColumn, rowNumber});
        Field newRookField = boardService.getField(new String[]{newRookColumn, rowNumber});

        newKingField.setFigure(king);
        newRookField.setFigure(rook);

        oldRookField.setFigure(null);
        oldKingField.setFigure(null);

        Board board = gameService.getCurrentBoard();

        //set last played
        board.setLastPlayed(king.getFigureColor());

        //create history entry
        GameState historyGameState = gameService.getCopyGameState();
        History history = new History(sourceField, targetField, king, rook, MoveType.CASTLING, historyGameState);
        board.addMoveToHistory(history);

        //castling done, reset state
        gameService.getCurrentGameState().setCastling(false);
    }

    private void revertCastling(History history) {

    }


}
