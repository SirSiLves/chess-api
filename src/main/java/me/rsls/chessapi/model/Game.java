package me.rsls.chessapi.model;

public class Game {

    private final Board board;
    private final Player[][] players;

    public Game(Player[][] players) {
        this.board = new Board();
        this.players = players;
    }

    public Player[][] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
