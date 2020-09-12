package me.rsls.chessapi.controller;


import me.rsls.chessapi.exception.model.ApiException;
import me.rsls.chessapi.model.Game;
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


    @RequestMapping(value = "getGamePicture", method = RequestMethod.GET)
    public ResponseEntity<Game> getGamePicture() {
        Game game = gameService.getGamePicture();
        if (game != null) {
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            throw new ApiException("The game is not yet initialized", HttpStatus.OK);
        }
    }
}
