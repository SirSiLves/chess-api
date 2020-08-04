package me.rsls.chessapi.service;


import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class MoveService {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BoardService boardService;



    public boolean validateMove(ClickedFields clickedFields){

        Board tempBoard = gameService.getGamePicture(playerService.getPlayer()).getBoard();

        Field sourceField = boardService.getField(tempBoard, clickedFields.getSourceField());
        Field targetField = boardService.getField(tempBoard, clickedFields.getTargetField());

        this.setMove(sourceField, targetField);

        return true;
    }

    public void setMove(Field sourceField, Field targetField){
        Figure tempFigure = sourceField.getFigure();
        targetField.setFigure(tempFigure);
        sourceField.setFigure(null);
    }

}
