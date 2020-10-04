package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
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
            if (player != null && g.getPlayer().getSessionId().equals(player.getSessionId())) {
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
        historyGameState.setPromotion(this.getCurrentGameState().isPromoted());
        historyGameState.setRemisReason(this.getCurrentGameState().getRemisReason());
        historyGameState.setCastling(this.getCurrentGameState().isCastling());

        return historyGameState;
    }

    public void revertGameState() {
        Board board = this.getCurrentBoard();
        History history = board.getMoveHistory().get(board.getMoveHistory().size() - 1);

        GameState currentGameState = this.getCurrentGameState();
        if(history != null) {
            GameState historyGameState = history.getGameState();

            currentGameState.setCheck(historyGameState.isCheck());
            currentGameState.setDoubleCheck(historyGameState.isDoubleCheck());
            currentGameState.setCheckColor(historyGameState.getCheckColor());
            currentGameState.setCheckMate(historyGameState.isCheckMate());
            currentGameState.setWinner(historyGameState.getWinner());
            currentGameState.setRemis(historyGameState.isRemis());
            currentGameState.setRemisReason(historyGameState.getRemisReason());
            currentGameState.setPromotion(historyGameState.isPromoted());
            currentGameState.setCastling(historyGameState.isCastling());
        }
        else{
            board.setLastPlayed(null);
        }
    }

//    public void switchPlayer() {
//        Board board = this.getCurrentBoard();
//
//        board.getFigureArrayList().forEach( f -> {
//            if(f.getFigureColor().equals(Color.BLACK)) {
//                f.setFigureColor(Color.WHITE);
//            }
//            else if(f.getFigureColor().equals(Color.WHITE)) {
//                f.setFigureColor(Color.BLACK);
//            }
//        });
//
//    }
}
