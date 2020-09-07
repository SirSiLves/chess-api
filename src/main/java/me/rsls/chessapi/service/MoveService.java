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
    private BoardService boardService;

    @Autowired
    private ValidateService validateService;


    public Validation handleMove(String[] sourceDesignation, String[] targetDesignation){

        Field sourceField = boardService.getField(sourceDesignation);
        Field targetField = boardService.getField(targetDesignation);

        Validation validation = validateService.validateMove(sourceField, targetField);

        //if validation = true -> execute move
        if(validation.isState()) {
            this.setMove(sourceField, targetField);
        }

        return validation;
    }

    public void setMove(Field sourceField, Field targetField){
        Board board = gameService.getCurrentBoard();

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

    }

}
