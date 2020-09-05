package me.rsls.chessapi.service;


import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MoveService {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ValidateService validateService;


    public Validation handleMove(String[] sourceDesignation, String[] targetDesignation){

        Board tempBoard = gameService.getGamePicture(playerService.getPlayer()).getBoard();

        Field sourceField = boardService.getField(tempBoard, sourceDesignation);
        Field targetField = boardService.getField(tempBoard, targetDesignation);

        Validation validation = validateService.validateMove(tempBoard, sourceField, targetField);

        //if validation = true -> execute move
        if(validation.isState()) {
            this.setMove(tempBoard, sourceField, targetField);
        }

        return validation;
    }

    public void setMove(Board board, Field sourceField, Field targetField){

        //set eliminated figure
        if(targetField.getFigure() != null) targetField.getFigure().setAlive(false);

        //set moved figure
        Figure movedFigure = sourceField.getFigure();
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);

        //create history entry
        Move historyEntry = new Move(sourceField, targetField, movedFigure);
        board.addMoveToHistory(historyEntry);

        //set last played
        board.setLastPlayed(movedFigure.getFigureColor());

        //set winner
        if(board.getCheck().isCheckMate()) {
            gameService.getGamePicture(playerService.getPlayer()).setWinner(movedFigure.getFigureColor());
        }
    }

}
