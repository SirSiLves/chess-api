package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemisService {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private CheckService checkService;


    public void validateRemis(Field sourceField, Field targetField) {
        Board board = gameService.getCurrentBoard();

        moveExecutorService.executeMove(sourceField, targetField);

        boolean figureWithPossibleFields;

        if(board.getLastPlayed().equals(Color.BLACK)){
            figureWithPossibleFields = existsPossibleFields(Color.WHITE);
        }
        else {
            figureWithPossibleFields = existsPossibleFields(Color.BLACK);
        }

        if(!figureWithPossibleFields) board.getCheck().setRemis(true);

        moveExecutorService.revertLastMove();
    }

    private boolean existsPossibleFields(Color figureColor){
        Board board = gameService.getCurrentBoard();

        List<Figure> alliesFigures = board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> f.getFigureColor().equals(figureColor))
                .collect(Collectors.toList());


        boolean possibleFields = false;

        possibleFields = CheckMateService.isCanProtect(possibleFields, alliesFigures, figureService, validFieldService, checkService);

        return possibleFields;



    }

}
