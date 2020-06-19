package me.rsls.chessapi.model;

public class Player {

    private String nickName;
    private final String sessionId;

    public Player(String nickName, String sessionId) {
        this.nickName = nickName;
        this.sessionId = sessionId;
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
