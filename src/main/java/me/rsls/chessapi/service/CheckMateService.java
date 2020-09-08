package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckMateService {

    @Autowired
    private GameService gameService;


    public void validateCheckMate() {
        Board board = gameService.getCurrentBoard();



    }
}
