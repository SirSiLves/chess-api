package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
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

    public void createGame(Player[] players){
        gameArrayList.add(new Game(players));
    }

    public Board getGamePicture(Player player){
        for(Game g : gameArrayList){
            for(Player p : g.getPlayers()){
                if(p.getSessionId().equals(player.getSessionId())){
                    return g.getBoard();
                }
            }
        }
        return null;
    }
}
