package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Color;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.Figure;
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
    private FigureService figureService;

    @Autowired
    private ValidFieldService validFieldService;


    public void validateCheckMate(Field kingField, List<Figure> enemyList) {
        Board board = gameService.getCurrentBoard();




//    player.startTurn();
//        if (player.isInCheck()
//        if(player.king.hasNoLegalMoves() && player.cannotProtectKing())
//            game.checkMate(player)




    }
}
