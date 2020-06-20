package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializeService {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    public void initializePlayer(){

    }

    public void initializeGame(){
        Player bot = playerService.getBot();
        //TODO player object not exist
        Player[] players = {bot, playerService.getPlayer()};

        gameService.createGame(players);
    }
}
