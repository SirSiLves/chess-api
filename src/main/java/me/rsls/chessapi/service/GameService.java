package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.model.GameState;
import me.rsls.chessapi.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class GameService {

    @Autowired
    private PlayerService playerService;

    private final ArrayList<Game> gameArrayList;

    public GameService() {
        gameArrayList = new ArrayList<>();
    }

    public void createGame(Player player, Board board) {
        gameArrayList.add(new Game(player, board));
    }

    public Game getGamePicture() {
        Player player = playerService.getPlayer();

        Game tempGame = null;

        for (Game g : gameArrayList) {
            if (g.getPlayer().getSessionId().equals(player.getSessionId())) {
                if(tempGame == null || (g.getCreateDate().compareTo(tempGame.getCreateDate()) > 0)){
                    tempGame = g;
                }
            }
        }
        return tempGame;
    }

    public Board getCurrentBoard(){
        return this.getGamePicture().getBoard();
    }

    public GameState getCurrentGameState(){
        return this.getGamePicture().getGameState();
    }


    public GameState getCopyGameState() {

        //collect last gamestate
        GameState historyGameState = new GameState();
        historyGameState.setCheck(this.getCurrentGameState().isCheck());
        historyGameState.setCheckMate(this.getCurrentGameState().isCheckMate());
        historyGameState.setRemis(this.getCurrentGameState().isRemis());
        historyGameState.setCheckColor(this.getCurrentGameState().getCheckColor());
        historyGameState.setWinner(this.getCurrentGameState().getWinner());
        historyGameState.setDoubleCheck(this.getCurrentGameState().isDoubleCheck());
        historyGameState.setPawnChange(this.getCurrentGameState().isPawnChange());
        historyGameState.setRemisReason(this.getCurrentGameState().getRemisReason());

        return historyGameState;
    }
}
