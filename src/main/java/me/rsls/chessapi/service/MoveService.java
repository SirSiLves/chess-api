package me.rsls.chessapi.service;


import me.rsls.chessapi.model.*;
import me.rsls.chessapi.service.validation.ValidateService;
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


    public Validation handleMove(ClickedFields clickedFields){

        Board tempBoard = gameService.getGamePicture(playerService.getPlayer()).getBoard();

        Field sourceField = boardService.getField(tempBoard, clickedFields.getSourceField());
        Field targetField = boardService.getField(tempBoard, clickedFields.getTargetField());


        Validation validation = validateService.validateMove(tempBoard, sourceField, targetField);

        //if validation = true -> execute move
        if(validation.isState()) this.setMove(tempBoard, sourceField, targetField);

        return validation;
    }

    public void setMove(Board board, Field sourceField, Field targetField){

        Figure movedFigure = sourceField.getFigure();

        //create history entry
        Move historyEntry = new Move(sourceField, targetField, movedFigure);
        board.addMoveToHistory(historyEntry);

        //set moved figure
        targetField.setFigure(movedFigure);
        sourceField.setFigure(null);

        //set last played
        board.setLastPlayed(movedFigure.getFigureColor());
    }

}
