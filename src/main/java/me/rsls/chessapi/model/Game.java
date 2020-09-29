package me.rsls.chessapi.model;

import java.util.Date;

public class Game {

    private final Board board;
    private final Player player;
    private final Date createDate;
    private GameState gameState;

    public Game(Player player, Board board) {
        this.board = board;
        this.player = player;
        this.createDate = new Date();
        this.gameState = new GameState();
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
