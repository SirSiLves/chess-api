package me.rsls.chessapi.model;

import java.util.Date;

public class Game {

    private final Board board;
    private final Player[] players;
    private Player winnerPlayer;
    private final Date createDate;

    public Game(Player[] players, Board board) {
        this.board = board;
        this.players = players;
        this.createDate = new Date();
        this.winnerPlayer = null;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winnerPlayer;
    }

    public void setWinner(Player winner) {
        this.winnerPlayer = winner;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
