package me.rsls.chessapi.model;

import java.util.Date;

public class Game {

    private final Board board;
    private final Player player;
    private Color winnerPlayer;
    private final Date createDate;

    public Game(Player player, Board board) {
        this.board = board;
        this.player = player;
        this.createDate = new Date();
        this.winnerPlayer = null;
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public Color getWinner() {
        return winnerPlayer;
    }

    public void setWinner(Color winnerColor) {
        this.winnerPlayer = winnerColor;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
