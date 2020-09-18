package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Bot;
import me.rsls.chessapi.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayerService {

    @Autowired
    SessionService sessionService;

    private final ArrayList<Player> playerArrayList;

    public PlayerService(){
        playerArrayList = new ArrayList<>();
        playerArrayList.add(new Bot());
    }

    public void addPlayer(String nickName){
        Player player = this.getPlayer();
        if (player == null){
            playerArrayList.add(new Player(nickName, sessionService.getHttpSessionId()));
        }
    }

    public Player getPlayer(){
        for(Player p : playerArrayList){
            if(p.getSessionId().equals(sessionService.getHttpSessionId())) return p;
        }
        return null;
    }

    public Bot getBot(){
        //TODO
        return (Bot) playerArrayList.get(0);
    }
}


