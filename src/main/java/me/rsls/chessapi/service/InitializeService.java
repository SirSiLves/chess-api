package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializeService {

    @Autowired
    GameService gameService;

    @Autowired
    BoardService boardService;

    @Autowired
    PlayerService playerService;

    @Autowired
    FigureService figureService;


    public void initializeGame(){

        playerService.addPlayer("TEST USER01");
        Player custom = playerService.getPlayer();

        Board tempBoard = boardService.createBoard();
        figureService.createFigures(tempBoard);

        gameService.createGame(custom, tempBoard);
    }

}
