package me.rsls.chessapi.controller;


import me.rsls.chessapi.exception.model.ApiException;
import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Player;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;


    @RequestMapping(value = "getGamePicture", method = RequestMethod.GET)
    public ResponseEntity<Board> getGamePicture() {
        Player tempPlayer = playerService.getPlayer();
        if (gameService.getGamePicture(tempPlayer) != null) {
            return new ResponseEntity<>(gameService.getGamePicture(tempPlayer), HttpStatus.OK);
        } else {
            throw new ApiException("The game is not yet initialized", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
