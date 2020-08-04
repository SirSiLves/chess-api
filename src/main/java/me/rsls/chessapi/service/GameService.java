package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class GameService {

    private final ArrayList<Game> gameArrayList;

    public GameService() {
        gameArrayList = new ArrayList<>();
    }

    public void createGame(Player[] players, Board board) {
        gameArrayList.add(new Game(players, board));
    }

    public Game getGamePicture(Player player) {
        Game tempGame = null;

        for (Game g : gameArrayList) {
            for (Player p : g.getPlayers()) {
                if (p.getSessionId().equals(player.getSessionId())) {
                    if(tempGame == null || (g.getCreateDate().compareTo(tempGame.getCreateDate()) > 0)){
                        tempGame = g;
                    }
                }
            }
        }
        return tempGame;
    }
}
