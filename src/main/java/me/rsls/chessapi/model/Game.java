package me.rsls.chessapi.model;

public class Game {

    private final Board board;
    private final Player[] players;
    private Player winnerPlayer;

    public Game(Player[] players, Board board) {
        this.board = board;
        this.players = players;
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
}
