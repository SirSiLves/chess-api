package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    private final ArrayList<Game> gameArrayList;

    public GameService(){
        gameArrayList = new ArrayList<>();
    }

    public void createGame(Player[][] players){
        gameArrayList.add(new Game(players));
    }
}
