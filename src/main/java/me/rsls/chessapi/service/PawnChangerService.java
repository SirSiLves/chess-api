package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Figure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PawnChangerService {

    @Autowired
    private GameService gameService;

    public Figure getSelectedFigure(){

//        gameService.getCurrentBoard().getLastPlayed()


        return null;
    }

}
