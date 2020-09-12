package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MoveExecutorService {

    @Autowired
    private GameService gameService;


    public void executeMove(Field sourceField, Field targetField) {
        Board board = gameService.getCurrentBoard();

        Figure movedFigure = sourceField.getFigure();
        Figure killedFigure = targetField.getFigure();

        //set eliminated figure
        if (killedFigure != null) killedFigure.setAlive(false);

        //set moved figure
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);

        //create history entry
        Move move = new Move(sourceField, targetField, movedFigure, killedFigure);
        board.addMoveToHistory(move);

        //set last played
        board.setLastPlayed(movedFigure.getFigureColor());
    }


    public void revertLastMove() {
        Board board = gameService.getCurrentBoard();

        Move lastMove = board.getMoveHistory().get(board.getMoveHistory().size() - 1);

        Field lastSourceField = lastMove.getSourceField();
        Field lastTargetField = lastMove.getTargetField();
        Figure movedFigure = lastMove.getMovedFigure();
        Figure killedFigure = lastMove.getKilledFigure();

        //revive killed figure
        if(killedFigure != null ) killedFigure.setAlive(true);

        //move back to start
        lastTargetField.setFigure(killedFigure);
        lastSourceField.setFigure(movedFigure);

        //delete last entry in history
        board.getMoveHistory().entrySet().removeIf( m -> m.getValue().equals(lastMove));

        //set last played
        if(movedFigure.getFigureColor().equals(Color.BLACK)) board.setLastPlayed(Color.WHITE);
        else board.setLastPlayed(Color.BLACK);
    }


}
