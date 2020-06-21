package me.rsls.chessapi.model;

public class Player {

    private String nickName;
    private final String sessionId;
    private Color playingColor;

    public Player(String nickName, String sessionId) {
        this.nickName = nickName;
        this.sessionId = sessionId;
        this.playingColor = null;
    }

    public Color getPlayingColor() {
        return playingColor;
    }

    public void setPlayingColor(Color playingColor) {
        this.playingColor = playingColor;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSessionId() {
        return sessionId;
    }
}
